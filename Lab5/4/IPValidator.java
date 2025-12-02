import java.util.regex.*;

public class IPValidator {
    public static boolean isValidIP(String ip) {
        String regex = "^((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)$";
        return ip.matches(regex);
    }

    public static void main(String[] args) {
        String[] ips = {"192.168.1.1", "256.0.0.1", "127.0.0.1", "0.0.0.0", "255.255.255.255", "01.02.03.04"};
        
        for (String ip : ips) {
            System.out.println(ip + " -> " + (isValidIP(ip) ? "Валиден" : "Невалиден"));
        }
    }
}