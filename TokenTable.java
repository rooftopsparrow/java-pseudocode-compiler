import java.util.HashMap;

public class TokenTable {

    private HashMap<String, Integer> table;

    public TokenTable() {

        this.table = new HashMap<String, Integer>();
        this.table.put("IF", 1);
        this.table.put("THEN", 2);
        this.table.put("ELSE", 3);
        this.table.put("FI", 4);
        this.table.put("LOOP", 5);
        this.table.put("BREAK", 6);
        this.table.put("READ", 7);
        this.table.put("PRINT", 8);
        this.table.put("AND", 9);
        this.table.put("OR", 0);
        this.table.put("END", 31);
        this.table.put(".", 11);
        this.table.put(")", 12);
        this.table.put("(", 13);
        this.table.put("/", 14);
        this.table.put("*", 15);
        this.table.put("-", 16);
        this.table.put("+", 17);
        this.table.put("<>", 18);
        this.table.put(">", 19);
        this.table.put(">=", 20);
        this.table.put("=", 21);
        this.table.put("<=", 22);
        this.table.put("<", 23);
        this.table.put(":=", 24);
        this.table.put(";", 25);
        this.table.put("SPACE", 26);
        this.table.put("EOL", 27);
        this.table.put("IDENTIFIER", 28);
        this.table.put("NUMBER", 29);
        this.table.put("STRING", 3);

    } 

    public boolean isToken(String s) {
        return this.table.containsKey(s);
    }

    public int getCode(String s) {
        return this.table.get(s);
    }

}
