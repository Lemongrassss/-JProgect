import java.util.List;
import java.util.stream.Collectors;

public class AggregateProcessor {
    @DataProcessor(name = "aggregate", priority = 3)
    public List<String> aggregateData(List<String> data) {
        return data.stream()
                   .sorted()
                   .distinct()
                   .collect(Collectors.toList());
    }
}
