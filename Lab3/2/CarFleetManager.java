import java.util.HashMap;
import java.util.Scanner;

public class CarFleetManager {
    private HashMap<String, Car> carMap;

    public CarFleetManager() {
        carMap = new HashMap<>();
    }


    public void addCar(String licensePlate, String brand, String model, int year) {
        Car car = new Car(brand, model, year);
        carMap.put(licensePlate, car);
        System.out.println("Автомобиль добавлен: " + licensePlate + " -> " + car);
    }

    
    public void findCar(String licensePlate) {
        Car car = carMap.get(licensePlate);
        if (car != null) {
            System.out.println("Найден автомобиль: " + licensePlate + " -> " + car);
        } else {
            System.out.println("Автомобиль с номером " + licensePlate + " не найден");
        }
    }

    
    public void removeCar(String licensePlate) {
        Car removedCar = carMap.remove(licensePlate);
        if (removedCar != null) {
            System.out.println("Автомобиль удален: " + licensePlate + " -> " + removedCar);
        } else {
            System.out.println("Автомобиль с номером " + licensePlate + " не найден для удаления");
        }
    }

    
    public void displayAllCars() {
        if (carMap.isEmpty()) {
            System.out.println("В автопарке нет автомобилей");
            return;
        }

        System.out.println("\n=== АВТОПАРК ===");
        System.out.println("Всего автомобилей: " + carMap.size());
        for (String licensePlate : carMap.keySet()) {
            Car car = carMap.get(licensePlate);
            System.out.println(licensePlate + " -> " + car);
        }
    }

    
    public void getCarCount() {
        System.out.println("Количество автомобилей в автопарке: " + carMap.size());
    }

    
    public void clearFleet() {
        carMap.clear();
        System.out.println("Автопарк полностью очищен");
    }

    public static void main(String[] args) {
        CarFleetManager manager = new CarFleetManager();

        System.out.println("=== ДЕМОНСТРАЦИЯ РАБОТЫ С АВТОПАРКОМ ===\n");

        
        System.out.println("1. Добавление автомобилей:");
        manager.addCar("A123BC", "Toyota", "Camry", 2020);
        manager.addCar("B456DE", "BMW", "X5", 2019);
        manager.addCar("C789FG", "Audi", "A4", 2021);
        manager.addCar("D012HI", "Mercedes", "C-Class", 2022);

        
        manager.displayAllCars();

        
        System.out.println("\n2. Поиск автомобилей:");
        manager.findCar("B456DE");
        manager.findCar("X999YY");

        
        System.out.println("\n3. Удаление автомобиля:");
        manager.removeCar("D012HI");

        
        System.out.println("\n4. Итоговое состояние автопарка:");
        manager.displayAllCars();

        
        manager.getCarCount();
    }
}