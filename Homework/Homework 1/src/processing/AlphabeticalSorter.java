package processing;

import io.Line;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AlphabeticalSorter implements Sorter {

    @Override
    public List<Line> sortLines(List<Line> lines) {
        return lines.stream().sorted(Comparator.comparing(Line::getText, String.CASE_INSENSITIVE_ORDER)).collect(Collectors.toList());
    }
}
