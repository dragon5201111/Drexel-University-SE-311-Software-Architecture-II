package process;

import line.Line;
import line.LineStorage;

import java.util.*;

public abstract class Indexer {
    protected Map<String, Set<Integer>> indexMap;
    protected LineStorage lineStorage;

    public Indexer(LineStorage lineStorage) {
        this.indexMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        this.lineStorage = lineStorage;
        this.initIndexMap();
    }

    private void initIndexMap() {
        List<Line> lines = lineStorage.getLines();
        for (Line line : lines) {

            int lineNumber = line.getLineNumber();
            for(String word : line.getWords()) {
                this.indexMap.computeIfAbsent(word, k -> new HashSet<>());
                this.indexMap.get(word).add(lineNumber);
            }
        }
    }

    protected abstract void preprocess();

    public List<String> getKeywords(){
        this.preprocess();

        List<String> keywords = new ArrayList<>();
        for(Map.Entry<String, Set<Integer>> entry : this.indexMap.entrySet()) {
            String word = entry.getKey();
            Set<Integer> lineNumbers = entry.getValue();

            keywords.add(word + ", " + lineNumbers.toString());
        }

        return keywords;
    }
}
