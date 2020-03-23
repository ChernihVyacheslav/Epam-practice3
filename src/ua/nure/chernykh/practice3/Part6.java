package ua.nure.chernykh.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {

    public static void main(String[] args) {
        String str = Util.readFile("part6.txt");
        System.out.println(convert(str));
    }

    /* Output should be:
        This _is _a _test
        _And this _is _also _a _test
        _And these are _also tests
        _test
        _Это _тест
        _Это _также _тест
        И это _также тесты
     */
    public static String convert(String input) {
        Pattern p = Pattern.compile("(?U)(?s)(?=\\b[^_])(\\w+)\\b(?:.*\\1\\b)+");
        Matcher m = p.matcher(input);
        while(m.find()) {
            input = input.replaceAll("\\b" + m.group(1) + "\\b", "_" + m.group(1));
            m = p.matcher(input);
        }
        return input;
    }

}
