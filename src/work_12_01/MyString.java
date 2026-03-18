package work_12_01;

public class MyString {

    public static void main(String[] args) {
        String str2 = "www";
        String str = new String("nww");
        char[] chars ={'w', 'w', 'w'};
        String s3 = new String(chars);


        /*
        \n - перенос
        \t - табуляция
        \\ - обратный слэш
        \"
        \'
         */

        String json = """
                {
                    "name": "Masha"
                }
                """;

        String s = "Hello";
        s.toUpperCase();


        // String Pool
        String a = "Hello";
        String b = "Hello";
        String c = new String("Hello");

        System.out.println(a==b);
        System.out.println(a==c);
        System.out.println(a.equals(c));

//        s.length();
//        s.charAt(0);
//        s.substring(0);
//        s.indexOf("2");
//        s.lastIndexOf(2);
//        s.contains("");
//        s.startsWith("");
//        s.endsWith("");
//        s.toUpperCase();
//        s.toLowerCase();
//        s.replace();
//        s.split(","); // "1,2,3" -> [1,2,3]
//        String.join(" ",{"1","2","3"});
//        s.trim();
//
//        StringBuilder sb = new StringBuilder();
//        sb.append("").append("").toString();
//
//
//
//
    }
}
