import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String str1 = "Hellowoooorld!!  and UUUnAAA ";
        String str2 = "hello.xml";
        System.out.println(str1 + " ->\n" + regexFirst(str1).toString());
        System.out.println(str1 + " ->\n" + regexFirstB(str1).toString());
        System.out.println(str2 + " ->\t" + regexSecond(str2));
    }

    public static StringBuilder regexFirst(String str) {
        long startTime = System.nanoTime();
        StringBuilder res = new StringBuilder();
        Pattern p = Pattern.compile("(.)\\1+");
        Matcher m = p.matcher(str);
        int start = 0;
        while (m.find()) {
            res.append(str, start, m.start()+1).append(m.group().length());
            start = m.end();
        }
        if (start != str.length())
            res.append(str.substring(start));
        System.out.println(System.nanoTime() - startTime);
        return res;
    }

    //второй вариант - быстрее
    public static StringBuilder regexFirstB(String str) {
        long startTime = System.nanoTime();
        StringBuilder res = new StringBuilder();
        Pattern p = Pattern.compile("(.)\\1*");
        Matcher m = p.matcher(str);
        while (m.find()) {
            res.append(m.group(1));
            int length = m.group().length();
            if (length > 1)
                res.append(length);
        }
        System.out.println(System.nanoTime() - startTime);
        return res;
    }

    public static String regexSecond(String str) {
        Pattern p = Pattern.compile(".[^\\.]+$");
        Matcher m = p.matcher(str);
        if (m.find())
            return m.group().substring(1);
        else
            return "*** неверный ввод";
    }
}
