import static org.junit.Assert.*;

import org.junit.*;

public class TokenTableTest {

    private static TokenTable table;

    @BeforeClass
    public static void beforeClass() {
        table = new TokenTable(); 
    }

    @Test
    public void isTokenShouldReturnTrueIfToken() {
        assertTrue(table.isToken(":="));
        assertTrue(table.isToken(">"));
        assertTrue(table.isToken(")"));
        assertTrue(table.isToken("*"));
        assertTrue(table.isToken("IF"));
        assertTrue(table.isToken("EOL"));
    } 

    @Test
    public void isTokenShouldReturnFalseIfNotToken() {
        assertFalse(table.isToken("ABC"));
        assertFalse(table.isToken("  "));
        assertFalse(table.isToken(":"));
        assertFalse(table.isToken("\"Hello\""));
    }

}
