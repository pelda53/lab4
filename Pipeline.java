import java.util.List;
public class Pipeline implements AutoCloseable {
    private final Source<String> source;
    private final ParserStage parser;
    private final List<Stage<LogRecord, LogRecord>> stages;
    private final FileSink sink;
    public Pipeline(Source<String> source, ParserStage parser, List<Stage<LogRecord, LogRecord>> stages, FileSink sink) {
        this.source = source; this.parser = parser; this.stages = stages; this.sink = sink;
    }
    public void execute() throws Exception {
        sink.open();
        String line;
        while ((line = source.next()) != null) {
            LogRecord record = parser.process(line);
            if (record == null) continue;
            for (Stage<LogRecord, LogRecord> stage : stages) {
                record = stage.process(record);
                if (record == null) break;
            }
            if (record != null) { sink.consume(record); }
        }
        sink.close();
    }
    @Override public void close() throws Exception { sink.close(); }
}
