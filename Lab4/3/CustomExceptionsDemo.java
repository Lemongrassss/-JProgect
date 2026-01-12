import java.io.*;
import java.util.logging.*;

class CustomNumberFormatException extends Exception {
    public CustomNumberFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}

class ExceptionLogger {
    private static final Logger logger = Logger.getLogger(ExceptionLogger.class.getName());
    
    static {
        try {
            FileHandler handler = new FileHandler("exceptions.log", true);
            handler.setFormatter(new SimpleFormatter());
            logger.addHandler(handler);
            logger.setUseParentHandlers(false);
        } catch (IOException e) {
            System.err.println("Ошибка логгера: " + e.getMessage());
        }
    }
    
    public static void log(Exception e) {
        logger.severe(e.getClass().getName() + ": " + e.getMessage());
    }
}

public class CustomExceptionsDemo {
    
    public static int convertToInt(String str) throws CustomNumberFormatException {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new CustomNumberFormatException(
                "Ошибка преобразования '" + str + "' в число", e);
        }
    }
    
    public static void main(String[] args) {
        String[] tests = {"123", "45.6", "abc", "123abc", ""};
        
        System.out.println("Тест преобразования строк:");
        for (String test : tests) {
            try {
                int result = convertToInt(test);
                System.out.println("✓ '" + test + "' -> " + result);
            } catch (CustomNumberFormatException e) {
                System.out.println("✗ '" + test + "' -> " + e.getMessage());
                ExceptionLogger.log(e);
            }
        }
        
        System.out.println("\nЛоги сохранены в exceptions.log");
    }
}