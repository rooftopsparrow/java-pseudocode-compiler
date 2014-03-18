import static org.junit.Assert.*;

import org.junit.*;

public class TokenParserTransformTest {

    TokenParser parser = new TokenParser();

    @Test
    public void transformOfATokenShouldReturnTheToken() {
        Token t = parser.transform("<=");
        assertSame("TOKEN should have code 22", t.getCode(), 22);
        assertEquals("TOKEN should have value of <=", t.getValue(), "<=");
        assertEquals("TOKEN should have type of <=", t.getValue(), "<=");
        assertEquals(t.getType(), "<=");
    }

    @Test
    public void transformOfASpaceShouldReturnTheToken() {
        Token t = parser.transform(" ");
        assertSame("SPACE should have code 26", t.getCode(), 26);
        assertEquals("SPACE should have value \" \"", t.getValue(), " ");
        assertEquals("SPACE should have type SPACE", t.getType(), "SPACE");
    }

    @Test
    public void transformOfAnEOLShouldReturnTheToken() {
        Token t = parser.transform("\n");
        assertSame("EOL should have code 27", t.getCode(), 27);
        assertEquals("EOL should have value \\n", t.getValue(), "\n");
        assertEquals("EOL should have type EOL", t.getType(), "EOL");
    }

    @Test
    public void transformOfAStringShouldReturnTheToken() {
        Token t = parser.transform("\"HELLO\"");
        assertSame("STRING should have value 3", t.getCode(), 3);
        assertEquals("STRING should have value \"HELLO\"", t.getValue(), "\"HELLO\"");
        assertEquals("STRING should have type STRING", t.getType(), "STRING");
    }

    @Test
    public void transformOfANumberShouldReturnTheToken() {
        Token t = parser.transform("10.124");
        assertSame("NUMBER should have value 29", t.getCode(), 29);
        assertEquals("NUMBER should have value 10.124", t.getValue(), "10.124");
        assertEquals("NUMBER should have value NUMBER", t.getType(), "NUMBER");
    }

    @Test
    public void transformOfAnIdentifierShouldReturnTheToken() {
        Token t = parser.transform("ABC");
        assertSame("IDENTIFIER should have value 28", t.getCode(), 28);
        assertEquals("IDENTIFIER should have value ABC", t.getValue(), "ABC");
        assertEquals("IDENTIFIER should have value IDENTIFIER", t.getType(), "IDENTIFIER");
    }

}
