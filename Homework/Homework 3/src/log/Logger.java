package log;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {
    private static Logger logger;
    private final String fileName;

    private Logger(){
        this.fileName = "log.txt";
    }

    public synchronized static Logger getLogger() throws IOException {
        if (Logger.logger == null) {
            Logger.logger = new Logger();
        }

        return Logger.logger;
    }

    public synchronized void log(LogLevel logLevel, String message) throws IOException {
        try (FileWriter fileWriter = new FileWriter(this.fileName, true)){
            String log = String.format("[%s] [%s]: %s\n",
                    logLevel.getLevel(),
                    LocalDateTime.now(),
                    message);
            fileWriter.write(log);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
