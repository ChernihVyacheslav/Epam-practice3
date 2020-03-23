package ua.nure.chernykh.practice3;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    private static final String SEPARATOR = System.lineSeparator();
    private static final int MAX_PASSWORD_VALUE = 9999;

    public static void main(String[] args) {
        String str = Util.readFile("part1.txt");
        System.out.println("- convert 1:");
        System.out.println(convert1(str));
        System.out.println("- convert 2:");
        System.out.println(convert2(str));
        System.out.println("- convert 3:");
        System.out.println(convert3(str));
        System.out.println("- convert 4:");
        System.out.println(convert4(str));
    }

    /* The output of convert1 should be as following:
     ivanov: ivanov@mail.com
     петров: petrov@google.com
     obama: obama@google.com
     bush: bush@mail.com
     */
    public static String convert1(String input) {
        String[] lines = splitOnLines(input);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < lines.length; i++) {
            String[] parts = splitLine(lines[i]);
            sb.append(parts[0]).append(": ").append(parts[2]);
            if(i != lines.length - 1) {
                sb.append(SEPARATOR);
            }
        }
        return sb.toString();
    }


    /* The output of convert2 should be as following:
    Ivanov Ivan (email: ivanov@mail.com)
    Петров Петр (email: petrov@google.com)
    Obama Barack (email: obama@google.com)
    Буш Джордж (email: bush@mail.com)
     */
    public static String convert2(String input) {
        String[] lines = splitOnLines(input);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < lines.length; i++) {
            String[] parts = splitLine(lines[i]);
            String[] name = parts[1].split(" ");
            sb.append(name[1]).append(" ").append(name[0]).append(" (email: ").append(parts[2]).append(")");
            if(i != lines.length - 1) {
                sb.append(SEPARATOR);
            }
        }
        return sb.toString();
    }

    /* The output of convert3 should be as following:
        mail.com ==> ivanov, bush
        google.com ==> петров, obama
     */
    public static String convert3(String input) {
        StringBuilder sb = new StringBuilder();
        Pattern p = Pattern.compile("(?<=@).+");
        Matcher m = p.matcher(input);
        while(m.find()) {
            String domain = m.group();
            if(!sb.toString().contains(domain)) {
                sb.append(domain).append(" ==> ");
                Matcher m2 = Pattern.compile("(.+)(;.+;.+)" + domain).matcher(input);
                while(m2.find()) {
                    sb.append(m2.group(1)).append(", ");
                }
                sb.delete(sb.length() - 2, sb.length());
                sb.append(SEPARATOR);
            }
        }
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }

    /* The output of convert4 should be as following:
        Login;Name;Email;Password
        ivanov;Ivan Ivanov;ivanov@mail.com;1707
        петров;Петр Петров;petrov@google.com;9321
        obama;Barack Obama;obama@google.com;4623
        bush;Джордж Буш;bush@mail.com;7514
     */
    public static String convert4(String input) {
        StringBuilder sb = new StringBuilder();
        Pattern p = Pattern.compile("(?m).+$");
        Matcher m = p.matcher(input);
        while(m.find()) {
            String line = m.group();
            sb.append(line).append(";");
            if(line.contains("@")) {
                sb.append(String.format("%04d", getRandomIntegerBetweenRange(0, MAX_PASSWORD_VALUE)));
            } else {
                sb.append("Password");
            }
            sb.append(SEPARATOR);
        }
        return sb.toString();
    }

    public static int getRandomIntegerBetweenRange(double min, double max) {
        try {
            double x = (SecureRandom.getInstance("SHA1PRNG").nextDouble() * ((max - min) + 1)) + min;
            return (int) x;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No such algorithm");
        }
        return 0;
    }

    private static String[] splitOnLines(String input) {
        return input.split(SEPARATOR);
    }

    private static String[] splitLine(String input) {
        return input.split(";");
    }

}
