import java.util.regex.*;

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
       return new Token("a", "b", 3);
    }

}
