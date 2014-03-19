import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.InputStreamReader;

class getToken {

    public static void main(String[] args) {

        boolean stream = true; // By default
        boolean interactive = false; // REPL style
        boolean fromfile = false; // direct stream to file
        String filepath = null; // which file to read from
        boolean json = false; // output format
        boolean help = false; // display help or not

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-j":
                case "--json":
                    json = true;
                    break;
                case "-i":
                case "--interactive":
                    interactive = true;
                    break;
                case "-f":
                case "--file":
                    fromfile = true;
                    stream = false;
                    int next = ++i;
                    if (args.length <= next) help = true;
                    else filepath = args[next];
                    break;
                case "-h":
                case "--help":
                    help = true;
                default:
                    break;
            }
        }

        BufferedReader reader = null;

        if (help) {
            printHelp();
            return;
        } else if (fromfile) {
            try {
               reader = new BufferedReader( new FileReader(filepath) );
            } catch(Exception err) {
                System.err.println("File does not exist");
                printHelp(true);
                System.exit(1);
            }
        } else {
            try {
                reader = new BufferedReader( new InputStreamReader(System.in) );
            } catch(Exception err) {
                System.err.println("FATAL. Something bad happened.");
                printHelp(true);
                System.exit(2);
            }
        }

        TokenParser parser = new TokenParser();
        Token t = null;

        if (interactive) { // repl

            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("getToken -- interactive mode");
            System.out.println("To exit type ':quit'");
            while(true) {
                System.out.print("> ");
                String s = null;
                try {
                    s = buffer.readLine();
                } catch(IOException err) {
                    System.err.println("Bad read");
                    System.exit(1);
                }
                if (s.equals(":quit")) break;
                t = parser.getTokenObject(s);
                System.out.println(t.toJSON());
            }

        } else { // stream it up

            String data = getBuffer(reader);

            while ( data.length() > 0 ) {
                t = parser.getTokenObject(data);
                if (json)
                    System.out.println(t.toJSON());
                else {
                    StringBuilder s = new StringBuilder();
                    s.append(t.getEscapedValue()).append(" ").append(t.getCode());
                    System.out.println(s.toString());
                }

                data = data.substring(t.getValue().length());
            }
        }


    }

    private static String getBuffer(BufferedReader reader) {

        String line = null;
        String data = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        try {

            while(reader.ready() && ( line = reader.readLine() ) != null ) {
                stringBuilder.append( line );
                stringBuilder.append( ls );
            }

        } catch(IOException err) {
            System.err.println("Something bad happened");
            System.exit(1);
        }

        return stringBuilder.toString();

    }

    private static void printHelp() {
        printHelp(false);
    }

    private static void printHelp(boolean error) {
        StringBuilder help = new StringBuilder();
        String ls = System.getProperty("line.separator");

        help.append(ls)
            .append("  getToken -- pseudo code compiler")
            .append(ls)
            .append(ls)
            .append("  Usage: java getToken [options]")
            .append(ls)
            .append(ls)
            .append("  Options:")
            .append(ls)
            .append(ls)
            .append("   -h, --help\t\tDisplay this help\n")
            .append("   -f, --file <file>\tRead tokens from <file>\n")
            .append("   -i, --interactive\tREPL for trying tokens\n")
            .append("   -j, --json\t\tOutput JSON objects\n")
            .append(ls);

        if (error) System.err.print(help.toString());
        else System.out.print(help.toString());

    }
}