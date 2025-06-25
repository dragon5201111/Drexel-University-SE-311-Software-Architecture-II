package process;

import java.util.List;
import java.util.stream.Collectors;

public class AlphabeticalSorter extends Sorter {
    public AlphabeticalSorter(Shifter shifter) {
        super(shifter);
    }

    @Override
    protected List<String> generateSortedLines(List<String> lines) {
        return lines.stream()
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.toList());
    }
}
