public class Fun {

    public static void main(String[] args) {

//   1. Switch expression (standard)
        var day = "W";
        String result = switch (day) {
            case "M", "W", "F" -> "MWF";
            case "T", "TH", "S" -> "TTS";
            default -> "unknown";
        };
        System.out.println(result);

//   2. Instanceof pattern matching
        Object obj = "my string";
        if(obj instanceof String str){
            System.out.println( str.toUpperCase());
        }
//    3. text blocks, finally
       var block = """
              <html>
                  <body>
                      <p>Hello, world</p>
                  </body>
              </html>
                """;
        System.out.println(block);

//     4. records, basically a Kotlin data class
        var point = new Point(2,3);
        System.out.println(point);
    }
}
