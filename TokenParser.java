import java.util.regex.*;
import java.util.ArrayList;
import java.util.Arrays;

public class TokenParser {

    public TokenTable tokenTable = new TokenTable();

    // Return whether the string is a number
    public boolean isNumber(String s) {
        return Pattern.matches("^\\d*\\.?\\d+$", s);
    }

    // Return whether the string is a digit
    public boolean isDigit(String s) {
        return Pattern.matches("^\\d$", s);
    }

    // Return whether the String s is a decimal point
    // for the given fragment
    public boolean isDecimal(String fragment, String s) {
        return (s.equals(".") ? (fragment.replaceAll("[^\\.]", "").length() == 0) : false);
    }

    // Return whether the String is a space
    public boolean isSpace(String s) {
        return s.equals(" ");
    }

    // Return whether the String is an End Of Line character
    public boolean isEOL(String s) {
        return s.equals("\n");
    }

    public boolean isStringCharacter(String s) {
        return (s.equals("'") || s.equals("\""));
    }

    public boolean isSubString(String s) {
        return (s.indexOf('"') >= 0 || s.indexOf("'") >= 0);
    }

    public boolean isDelimiter(String s) {
        return (this.isSpace(s) || this.isEOL(s) || tokenTable.isToken(s));
    }

    public boolean isAssignment(String s, String c) {
        return (s.substring(s.length() - 1) + c).equals(":=");
    }

    public boolean isLogical(String s) {
        // return false;
        return (Pattern.matches("^>|>=|<>|=|<=|<$", s));
    }

    public Token transform(String fragment) {

        if (tokenTable.isToken(fragment))
            return new Token(fragment, fragment, tokenTable.getCode(fragment));

        if (this.isSpace(fragment))
          return new Token(fragment, "SPACE", tokenTable.getCode("SPACE"));

        if (this.isEOL(fragment))
            return new Token(fragment, "EOL", tokenTable.getCode("EOL"));

        if (this.isSubString(fragment))
            return new Token(fragment, "STRING", tokenTable.getCode("STRING"));

        if (this.isNumber(fragment))
           return new Token(fragment, "NUMBER", tokenTable.getCode("NUMBER"));

        return new Token(fragment, "IDENTIFIER", tokenTable.getCode("IDENTIFIER"));

    }

    public Token getTokenObject(String str) {

        ArrayList<String> chars = new ArrayList<String>(Arrays.asList(str.split("(?!^)")));
        String currentChar = "";
        String nextChar = "";
        String fragment = "";

        do {

            currentChar = chars.remove(0);
            fragment += currentChar; 
            nextChar = chars.isEmpty() ? "" : chars.get(0);

            // Assignment opperator check
            // ABC:= -> fragment ABC
            if (this.isAssignment(fragment, nextChar)) {
              if (fragment.equals(":")) fragment += nextChar;
              else fragment = fragment.substring(0, fragment.length() - 1);
            }

            // String sub loop
            if (this.isStringCharacter(fragment)) {
              String stringpart;
              do {
                stringpart = chars.isEmpty() ? "" : chars.remove(0); 
                fragment += stringpart;
              } while (!this.isStringCharacter(stringpart) && !chars.isEmpty());
              // TODO: THROW Exception for unbound strings
              // if (!isStringCharacter(stringpart)) {
                // throw new Exception("No end of string in data", str);
              // }
              break;
            }

            // Number sub loop
            if (this.isDigit(fragment)) {
              String numberpart = chars.isEmpty() ? "" : chars.remove(0);
              while (this.isDigit(numberpart) || this.isDecimal(fragment, numberpart)) {
                fragment += numberpart;
                numberpart = chars.isEmpty() ? "" : chars.remove(0);
              }
              // TODO: THROW Exception for bad number
              //if (!this.isNumber(fragment)) {
                // throw new Exception("Not a Number: " + fragment);
              // }
              break; 
            }
            // Logical opperator check <> >= <=
            if ( this.isLogical(fragment) && this.isLogical(nextChar) )  fragment += nextChar;

        } while (!this.isDelimiter(fragment) && !this.isDelimiter(nextChar) && !chars.isEmpty());

        return this.transform(fragment);

    }

}
