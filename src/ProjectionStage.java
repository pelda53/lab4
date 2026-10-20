import java.util.Set;
public class ProjectionStage implements Stage<LogRecord, LogRecord> {
    private final Set<String> fieldsToKeep;
    public ProjectionStage(String... fields) { this.fieldsToKeep = Set.of(fields); }
    @Override public LogRecord process(LogRecord record) {
        if (record == null) return null;
        return new LogRecord(
            fieldsToKeep.contains("timestamp") ? record.timestamp() : null,
            fieldsToKeep.contains("clientIp") ? record.clientIp() : null,
            fieldsToKeep.contains("method") ? record.method() : null,
            fieldsToKeep.contains("path") ? record.path() : null,
            fieldsToKeep.contains("status") ? record.status() : 0,
            fieldsToKeep.contains("responseSize") ? record.responseSize() : 0L
        );
    }
}
