import java.util.HashMap;

public class TokenTable {

    private HashMap<String, Integer> table;

    public TokenTable() {

        table = new HashMap<String, Integer>();
        table.put("IF", 1);
        table.put("THEN", 2);
        table.put("ELSE", 3);
        table.put("FI", 4);
        table.put("LOOP", 5);
        table.put("BREAK", 6);
        table.put("READ", 7);
        table.put("PRINT", 8);
        table.put("AND", 9);
        table.put("OR", 0);
        table.put("END", 31);
        table.put(".", 11);
        table.put(")", 12);
        table.put("(", 13);
        table.put("/", 14);
        table.put("*", 15);
        table.put("-", 16);
        table.put("+", 17);
        table.put("<>", 18);
        table.put(">", 19);
        table.put(">=", 20);
        table.put("=", 21);
        table.put("<=", 22);
        table.put("<", 23);
        table.put(":=", 24);
        table.put(";", 25);
        table.put("SPACE", 26);
        table.put("EOL", 27);
        table.put("IDENTIFIER", 28);
        table.put("NUMBER", 29);
        table.put("STRING", 3);

    } 

    public boolean isToken(String s) {
        return table.containsKey(s);
    }

    public int getCode(String s) {
        return table.get(s);
    }

}
