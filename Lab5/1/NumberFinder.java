import java.util.regex.*;

public class NumberFinder {
    public static void main(String[] args) {
        String text = "Цены: 99.99 руб, 1500, 7.5, 0.25, 1000000.";
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(text);
        
        System.out.println("Найденные числа:");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}