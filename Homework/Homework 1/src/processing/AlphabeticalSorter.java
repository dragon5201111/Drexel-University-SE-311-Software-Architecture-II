package processing;

import java.util.List;
import java.util.stream.Collectors;

public class AlphabeticalSorter implements Sorter {
    @Override
    public List<String> sortLines(List<String> lines) {
        return lines.stream().sorted(String::compareToIgnoreCase).collect(Collectors.toList());
    }
}
