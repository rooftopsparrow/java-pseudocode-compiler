# pseudocode-compiler

## getToken

getToken parses string input and tokenizes
into segments with seperate values.

CLI usage:

```
  getToken -- pseudocode-compiler
  Retrieve token values and codes

  Usage:  getToken [options]

  Options:

   -h, --help       Display this help
   -f, --file <file>    Read tokens from <file>
   -i, --interactive    REPL for trying tokens
   -j, --json       JSON output

  Examples:

    getToken < token.dat
      -- Read from stdin
    getToken -f token.dat
      -- Read from file
    echo 29.45 | getToken --json
      -- Output JSON

```
