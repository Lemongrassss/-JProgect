public class HashTableTest {
    public static void main(String[] args) {
        HashTable<String, Integer> hashTable = new HashTable<>(5);
        
        System.out.println("=== Тестирование HashTable ===");
        
        System.out.println("\n1. Добавление элементов:");
        hashTable.put("apple", 10);
        hashTable.put("banana", 20);
        hashTable.put("orange", 30);
        hashTable.put("grape", 40);
        hashTable.display();
        System.out.println("Размер таблицы: " + hashTable.size());
        
        System.out.println("\n2. Получение элементов:");
        System.out.println("apple -> " + hashTable.get("apple"));
        System.out.println("banana -> " + hashTable.get("banana"));
        System.out.println("orange -> " + hashTable.get("orange"));
        System.out.println("grape -> " + hashTable.get("grape"));
        System.out.println("unknown -> " + hashTable.get("unknown"));
        
        System.out.println("\n3. Обновление элемента:");
        hashTable.put("apple", 100);
        System.out.println("apple после обновления -> " + hashTable.get("apple"));
        hashTable.display();
        
        System.out.println("\n4. Удаление элемента:");
        hashTable.remove("banana");
        System.out.println("После удаления banana:");
        hashTable.display();
        System.out.println("Размер таблицы: " + hashTable.size());
        
        System.out.println("\n5. Проверка на пустоту:");
        System.out.println("Таблица пуста? " + hashTable.isEmpty());

        System.out.println("\n6. Тест коллизий:");
        HashTable<Integer, String> collisionTable = new HashTable<>(3);
        collisionTable.put(1, "one");
        collisionTable.put(4, "four"); 
        collisionTable.put(7, "seven"); 
        collisionTable.display();
        
    }
}