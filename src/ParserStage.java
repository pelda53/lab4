import java.time.LocalDateTime;
public class ParserStage implements Stage<String, LogRecord> {
    @Override public LogRecord process(String line) {
        if (line == null || line.isBlank()) return null;
        String[] parts = line.split("\\s+");
        if (parts.length < 6) return null;
        try {
            return new LogRecord(
                LocalDateTime.parse(parts[0]), parts[1], parts[2], parts[3],
                Integer.parseInt(parts[4]), Long.parseLong(parts[5])
            );
        } catch (Exception e) { return null; }
    }
}
