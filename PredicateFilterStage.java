import java.util.function.Predicate;
public class PredicateFilterStage implements Stage<LogRecord, LogRecord> {
    private final Predicate<LogRecord> predicate;
    public PredicateFilterStage(Predicate<LogRecord> predicate) { this.predicate = predicate; }
    @Override public LogRecord process(LogRecord record) {
        if (record == null) return null;
        return predicate.test(record) ? record : null;
    }
}
