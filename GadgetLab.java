import java.util.Scanner;

abstract class Gadget {
    protected String brand;
    protected double price;
    protected static int gadgetCount = 0;
    
    public Gadget(String brand, double price) {
        this.brand = brand;
        this.price = price;
        gadgetCount++;
    }
    
    public abstract void displayInfo();
    public abstract void performFunction();
    
    public void turnOn() {
        System.out.println(brand + " включен");
    }
    
    public String getBrand() { return brand; }
    public double getPrice() { return price; }
    public static int getGadgetCount() { return gadgetCount; }
}

class SmartWatch extends Gadget {
    private boolean heartRateMonitor;
    
    public SmartWatch(String brand, double price, boolean heartRateMonitor) {
        super(brand, price);
        this.heartRateMonitor = heartRateMonitor;
    }
    
    @Override
    public void displayInfo() {
        System.out.println("Часы " + brand + ", цена: " + price + " руб.");
        System.out.println("Измерение пульса: " + (heartRateMonitor ? "Да" : "Нет"));
    }
    
    @Override
    public void performFunction() {
        System.out.println(brand + ": Отслеживание активности");
    }
    
    public void measureHeartRate() {
        if (heartRateMonitor) {
            System.out.println("Пульс: 72 уд/мин");
        }
    }
}

class Smartphone extends Gadget {
    private int storageGB;
    
    public Smartphone(String brand, double price, int storageGB) {
        super(brand, price);
        this.storageGB = storageGB;
    }
    
    @Override
    public void displayInfo() {
        System.out.println("Смартфон " + brand + ", цена: " + price + " руб.");
        System.out.println("Память: " + storageGB + " GB");
    }
    
    @Override
    public void performFunction() {
        System.out.println(brand + ": Звонки и сообщения");
    }
    
    public void makeCall(String number) {
        System.out.println("Звонок на: " + number);
    }
}

class Laptop extends Gadget {
    private int ramGB;
    
    public Laptop(String brand, double price, int ramGB) {
        super(brand, price);
        this.ramGB = ramGB;
    }
    
    @Override
    public void displayInfo() {
        System.out.println("Ноутбук " + brand + ", цена: " + price + " руб.");
        System.out.println("ОЗУ: " + ramGB + " GB");
    }
    
    @Override
    public void performFunction() {
        System.out.println(brand + ": Работа и учеба");
    }
    
    public void runProgram(String program) {
        System.out.println("Запуск: " + program);
    }
}

public class GadgetLab {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Лабораторная работа: Гаджеты ===\n");
        
        SmartWatch watch = new SmartWatch("Apple Watch", 30000, true);
        Smartphone phone = new Smartphone("iPhone", 80000, 128);
        Laptop laptop = new Laptop("MacBook", 200000, 16);
        
        Gadget[] gadgets = {watch, phone, laptop};
        
        System.out.println("=== Демонстрация полиморфизма ===");
        for (Gadget gadget : gadgets) {
            gadget.turnOn();
            gadget.displayInfo();
            gadget.performFunction();
            System.out.println("---");
        }
        
        System.out.println("Всего гаджетов: " + Gadget.getGadgetCount());
        
        System.out.println("\n=== Специфичные методы ===");
        watch.measureHeartRate();
        phone.makeCall("+7-123-456-78-90");
        laptop.runProgram("Блокнот");
        
        System.out.println("\n=== Демонстрация инкапсуляции ===");
        System.out.println("Бренд телефона: " + phone.getBrand());
        System.out.println("Цена ноутбука: " + laptop.getPrice());
        
        System.out.println("\n=== Создание своего гаджета ===");
        System.out.print("Введите бренд смартфона: ");
        String brand = scanner.nextLine();
        
        System.out.print("Введите цену: ");
        double price = scanner.nextDouble();
        
        System.out.print("Введите память (GB): ");
        int storage = scanner.nextInt();
        
        Smartphone userPhone = new Smartphone(brand, price, storage);
        
        System.out.println("\n=== Ваш смартфон ===");
        userPhone.displayInfo();
        userPhone.performFunction();
        
        System.out.println("Всего гаджетов теперь: " + Gadget.getGadgetCount());
        
        scanner.close();
        
    }
}