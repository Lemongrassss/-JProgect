import java.util.regex.*;

public class LowerUpperFinder {
    public static void main(String[] args) {
        String text = "ПримерТекстГдеЕстьСлучаиНапримерТак";
        Pattern pattern = Pattern.compile("([a-zа-я])([A-ZА-Я])");
        Matcher matcher = pattern.matcher(text);
        
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(result, "!" + matcher.group(1) + matcher.group(2) + "!");
        }
        matcher.appendTail(result);
        
        System.out.println("Исходный текст: " + text);
        System.out.println("Результат: " + result);
    }
}