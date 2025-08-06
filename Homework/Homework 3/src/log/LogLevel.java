package log;

public enum LogLevel {
    INFO ("INFO");

    private final String level;
    LogLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return this.level;
    }
}
