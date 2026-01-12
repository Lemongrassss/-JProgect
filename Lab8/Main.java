public class Main {
    public static void main(String[] args) {
        DataManager manager = new DataManager();

        manager.registerDataProcessor(new FilterProcessor());
        manager.registerDataProcessor(new TransformProcessor());
        manager.registerDataProcessor(new AggregateProcessor());

        manager.loadData("data/input.txt");

        manager.processData();

        manager.saveData("data/output.txt");
        
        System.out.println("\nПервые 10 результатов:");
        manager.getProcessedData().stream()
               .limit(10)
               .forEach(System.out::println);
    }
}