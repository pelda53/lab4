import java.util.ArrayList;
import java.util.List;
public class PipelineBuilder {
    private Source<String> source;
    private ParserStage parser = new ParserStage();
    private final List<Stage<LogRecord, LogRecord>> stages = new ArrayList<>();
    public static PipelineBuilder from(Source<String> source) {
        PipelineBuilder builder = new PipelineBuilder(); builder.source = source; return builder;
    }
    public PipelineBuilder via(ParserStage parser) { this.parser = parser; return this; }
    public PipelineBuilder via(Stage<LogRecord, LogRecord> stage) { this.stages.add(stage); return this; }
    public Pipeline to(FileSink sink) { return new Pipeline(source, parser, stages, sink); }
}
