package process;

import java.util.List;
import java.util.stream.Collectors;

public class DescendingSorter extends Sorter {
    public DescendingSorter(Shifter shifter) {
        super(shifter);
    }

    @Override
    protected List<String> generateSortedLines(List<String> lines) {
        return lines.stream()
                .sorted(String.CASE_INSENSITIVE_ORDER.reversed())
                .collect(Collectors.toList());
    }
}
