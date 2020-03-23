package ua.nure.chernykh.practice3;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

    public static void main(String[] args) {
        String str = Util.readFile("part3.txt");
        System.out.println(convert(str));
    }

    /*
    Вход: текст (может состоять из латиницы и кириллицы).
    Выход: исходный текст, но регистр первого символа каждого слова,
    которое состоит из трех и более символов, должен быть инвертирован.

    Словом считать последовательность содержащую только буквы
    (все остальные символы в состав слова не входят).
    Создать статический метод convert, который преобразовывает вход в выход.
     */
    public static String convert(String input) {
        Pattern p = Pattern.compile("(?U)\\w{3,}");
        Matcher m = p.matcher(input);
        StringBuffer sb = new StringBuffer(input.length());
        while(m.find()) {
            String text = m.group();
            Character firstC = text.charAt(0);
            if(Character.isUpperCase(firstC)) {
                m.appendReplacement(sb, text.replaceFirst(".", (firstC.toString().toLowerCase(Locale.getDefault()))));
            } else {
                m.appendReplacement(sb, text.replaceFirst(".", (firstC.toString().toUpperCase(Locale.getDefault()))));
            }
        }
        return sb.toString();
    }
}
