package reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleReader implements Reader{
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public List<String> readLines() {
        List<String> lines = new ArrayList<>();
        while (true) {
            String line = scanner.nextLine();

            if (line.isEmpty()) {
                break;
            }

            lines.add(line);
        }

        return lines;
    }

    @Override
    public String readLine() {
        return this.scanner.nextLine();
    }
}
