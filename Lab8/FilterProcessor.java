import java.util.List;
import java.util.stream.Collectors;

public class FilterProcessor {
    @DataProcessor(name = "filter", priority = 1)
    public List<String> filterData(List<String> data) {
        return data.stream()
                   .filter(s -> s.startsWith("b") || s.startsWith("c"))
                   .collect(Collectors.toList());
    }
}
