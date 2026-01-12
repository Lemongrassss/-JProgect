import java.io.*;

public class FileCopyComplete {
    public static void main(String[] args) {
        String sourceFile = "source.txt";
        String destFile = "destination.txt";
        
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(sourceFile);
            fos = new FileOutputStream(destFile);
            System.out.println("Файлы успешно открыты.");

            byte[] buffer = new byte[1024];
            int bytesRead;
            int totalBytes = 0;

            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
                totalBytes += bytesRead;
            }

            System.out.println("Файл успешно скопирован. Скопировано байт: " + totalBytes);

        } catch (FileNotFoundException e) {
            System.out.println("Ошибка открытия файла: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка чтения/записи: " + e.getMessage());
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                    System.out.println("Исходный файл закрыт.");
                }
            } catch (IOException e) {
                System.out.println("Ошибка закрытия исходного файла: " + e.getMessage());
            }
            
            try {
                if (fos != null) {
                    fos.close();
                    System.out.println("Целевой файл закрыт.");
                }
            } catch (IOException e) {
                System.out.println("Ошибка закрытия целевого файла: " + e.getMessage());
            }
        }
    }
}