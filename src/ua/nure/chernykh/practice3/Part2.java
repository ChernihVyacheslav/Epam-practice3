package ua.nure.chernykh.practice3;

public class Part2 {

    private static final String SEPARATOR = System.lineSeparator();

    public static void main (String[] args) {
        String str = Util.readFile("part2.txt");
        System.out.println(convert(str));
    }

    public static String convert(String input) {
        String[] words = input.split("(?U)\\W");
        int maxLength = words[0].length();
        int minLength = words[0].length();
        for(int i = 1; i < words.length; i++) {
            if(words[i].length() > maxLength) {
                maxLength = words[i].length();
            }
            if(words[i].length() < minLength && words[i].length() > 0) {
                minLength = words[i].length();
            }
        }
        StringBuilder sb = new StringBuilder("Min: ");
        sb.append(getWords(minLength, words));
        sb.append(SEPARATOR).append("Max: ");
        sb.append(getWords(maxLength, words));
        return sb.toString();
    }

    private static String getWords(int wordLength, String[] words) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < words.length; i++) {
            if(words[i].length() == wordLength && !sb.toString().contains(words[i])) {
                sb.append(words[i]);
                sb.append(", ");
            }
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }
}
