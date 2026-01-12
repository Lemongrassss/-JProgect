import java.util.InputMismatchException;
import java.util.Scanner;

public class ArrayAverage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("Введите количество элементов массива: ");
            int n = scanner.nextInt();
            
            if (n <= 0) {
                throw new IllegalArgumentException("Количество элементов должно быть положительным числом");
            }
            
            int[] arr = new int[n];
            System.out.println("Введите элементы массива:");
            
            for (int i = 0; i < n; i++) {
                System.out.print("Элемент " + (i + 1) + ": ");
                arr[i] = scanner.nextInt();
            }
            
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += arr[i];
            }
            
            double average = (double) sum / n;
            System.out.println("Среднее арифметическое: " + average);
            
        } catch (InputMismatchException e) {
            System.out.println("Ошибка: Введено некорректное значение (ожидалось целое число)");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка: Выход за границы массива");
        } catch (Exception e) {
            System.out.println("Произошла непредвиденная ошибка: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("Программа завершена.");
        }
    }
}