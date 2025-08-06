package factory;

import process.AscendingSorter;
import process.DescendingSorter;
import process.Shifter;
import process.Sorter;

public class SorterFactory {
    public Sorter createSorter(Shifter shifter, boolean ascending) {
        if (ascending) {
            return new AscendingSorter(shifter);
        }

        return new DescendingSorter(shifter);
    }
}
