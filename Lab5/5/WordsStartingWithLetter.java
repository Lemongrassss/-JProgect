import java.util.regex.*;

public class WordsStartingWithLetter {
    public static void main(String[] args) {
        String text = "Яблоко апельсин арбуз банан вишня виноград";
        char startLetter = 'в';
        String regex = "\\b" + startLetter + "[а-яА-Яa-zA-Z]*\\b";
        
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher matcher = pattern.matcher(text);
        
        System.out.println("Слова, начинающиеся на '" + startLetter + "':");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}