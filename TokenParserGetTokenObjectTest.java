import static org.junit.Assert.*;

import org.junit.*;

public class TokenParserGetTokenObjectTest {

    TokenParser parser = new TokenParser();
    Token t;

    @Test
    public void assignmentOpperator() {
        t = parser.getTokenObject(":= 29");
        assertEquals("':= 29' should equal :=", ":=", t.getValue());
        assertEquals("':= 29' should be type :=", ":=", t.getType());
        t = parser.getTokenObject(":=29");
        assertEquals("':=29' should equal :=", ":=", t.getValue());
        assertEquals("':=29' should be type :=", ":=", t.getType());
    }

    @Test
    public void wholeAndDecimalNumbers() {
        t = parser.getTokenObject("19");
        assertEquals("19 should equal 19", "19", t.getValue());
        assertEquals("19 should be type NUMBER", "NUMBER", t.getType());
        t = parser.getTokenObject("42.63");
        assertEquals("42.63 should have value 42.63", "42.63", t.getValue());
        assertEquals("42.63 should be type NUMBER", "NUMBER", t.getType());
    }

    @Test
    public void subStrings() {
        t = parser.getTokenObject("\"Hello World!\"");
        assertEquals("'Hello World!' should have value 'Hello World!'", "\"Hello World!\"", t.getValue());
        assertEquals("'Hello World!' should have type STRING", "STRING", t.getType());
    }

    @Test
    public void logicalOperators() {
        t = parser.getTokenObject("<>");
        assertEquals("<> should have value <>", "<>", t.getValue());
        assertEquals("<> should have type <>", "<>", t.getType());
        t = parser.getTokenObject("<=");
        assertEquals("<= should have value <=", "<=", t.getValue());
        assertEquals("<= should have type <=", "<=", t.getType());
        t = parser.getTokenObject(">=");
        assertEquals(">= should have value >=", ">=", t.getValue());
        assertEquals(">= should have type >=", ">=", t.getType());
    }
}