import static org.junit.Assert.*;

import org.junit.*;

public class TokenParserTest {

    private TokenParser parser = new TokenParser();

    @Test
    public void isNumberShouldReturnTrueWhenANumber() {
        assertTrue("5 is a number", parser.isNumber("5"));
        assertTrue("5.0 is a number", parser.isNumber("5.0"));
        assertTrue(".5 is a number", parser.isNumber(".5"));
        assertTrue("1234.5 is a number", parser.isNumber("1234.5"));
        assertTrue("0.9 is a number", parser.isNumber("0.9"));
    }

    @Test
    public void isNumberShouldReturnFalseWhenNotANumber() {
        assertFalse("asdf is not a number", parser.isNumber("asdf"));
        assertFalse("5f is not a number", parser.isNumber("5f"));
        assertFalse(". is not a number", parser.isNumber("."));
        assertFalse("\"\" is not a number", parser.isNumber(""));
        assertFalse("+1 is not a number", parser.isNumber("+1"));
        assertFalse("11.123e is not a number", parser.isNumber("11.123e"));
    }

    @Test
    public void isDigitShouldReturnTrueWhenADigit() {
        assertTrue("1 is a digit", parser.isDigit("1"));
        assertTrue("0 is a digit", parser.isDigit("0"));
        assertTrue("9 is a digit", parser.isDigit("9"));
    }

    @Test
    public void isDigitShouldReturnFalseWhenNotADigit() {
        assertFalse("a is not a digit", parser.isDigit("a"));
        assertFalse(". is not a digit", parser.isDigit("."));
        assertFalse("< is not a digit", parser.isDigit("<"));
    }

    @Test
    public void isDecimalShouldReturnTrueWhenADecimal() {
        assertTrue("1. is a decimal", parser.isDecimal("1", "."));
    }

    @Test
    public void isDecimalShouldReturnFalseWhenNotADecimal() {
        assertFalse("1z is not a decimal point", parser.isDecimal("1", "z"));
        assertFalse("1.12. is not another decimal point", parser.isDecimal("1.12", "."));
    }

    @Test
    public void isSpaceShouldReturnTrueWhenASpace() {
        assertTrue("' ' is a space", parser.isSpace(" "));
    }

    @Test
    public void isSpaceShouldReturnFalseWhenNotASpace() {
        assertFalse("p is not a space", parser.isSpace("p"));
        assertFalse("\n is not a space", parser.isSpace("\n"));
    }

    @Test
    public void isEOLShouldReturnTrueWhenAnEOL() {
        assertTrue("\n is an EOL", parser.isEOL("\n"));
    }

    @Test
    public void isEOLShouldReturnFalseWhenNotAnEOL() {
        assertFalse("q is not an EOL", parser.isEOL("q"));
        assertFalse("' ' is not an EOL", parser.isEOL(" "));
    }

    @Test
    public void isStringCharacterShouldReturnTrueIfIsStringCharacter() {
        assertTrue("' is true", parser.isStringCharacter("'"));
        assertTrue("\" is true", parser.isStringCharacter("\""));
    }

    @Test
    public void isStringCharacterShouldReturnFalseIfIsNotStringCharacter() {
        assertFalse("a is not a string char", parser.isStringCharacter("a"));
        assertFalse("7 is not a string char", parser.isStringCharacter("7"));
    }

    @Test
    public void isDelimiterShouldReturnTrueWhenADelimiter() {
        assertTrue("' ' is a delimiter", parser.isDelimiter(" "));
        assertTrue("'\n' is a delimiter", parser.isDelimiter("\n"));
        assertTrue("'<' is a delimiter", parser.isDelimiter("<"));
        assertTrue("'IF' is a delimiter", parser.isDelimiter("IF"));
        assertTrue("')' is a delimiter", parser.isDelimiter(")"));
    }

    @Test
    public void isDelimiterShouldReturnFalseWhenNotADelimiter() {
        assertFalse("6.23 is not a delimiter",  parser.isDelimiter("6.23"));
        assertFalse("ABCD is not a delimiter",  parser.isDelimiter("ABCD"));
        assertFalse(": is not a delimiter",  parser.isDelimiter(":"));
    }

    @Test
    public void isAssignmentShouldReturnTrueWhenAssignmentToken() {
        assertTrue(":= is an assignmet opperator", parser.isAssignment(":", "="));
    }

    @Test
    public void isAssignmentShouldReturnFalseWhenNotAssignment() {
        assertFalse(":1 is not an assignmet opperator", parser.isAssignment(":", "1"));
    }

    @Test
    public void isSubstringShouldReturnTrueWhenContainsSubString() {
        assertTrue("'Hello' Contains a substring", parser.isSubString("'Hello'"));
        assertTrue("\"Hello\" Contains a substring", parser.isSubString("\"Hello\""));
    }

    @Test
    public void isSubStringShouldReturnFalseWhenDoesNotContainsSubString() {
        assertFalse("ARGS does not contain a substring", parser.isSubString("ARGS"));
    }


}
