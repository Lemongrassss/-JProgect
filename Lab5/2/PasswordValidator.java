import java.util.regex.*;

public class PasswordValidator {
    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,16}$";
        return password.matches(regex);
    }

    public static void main(String[] args) {
        String[] passwords = {"Pass123", "Password123", "PASS12345", "pass123", "Pa1", "Password123456789"};
        
        for (String pwd : passwords) {
            System.out.println(pwd + " -> " + (isValidPassword(pwd) ? "Валиден" : "Невалиден"));
        }
    }
}