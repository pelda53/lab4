import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
public class FileSink implements Sink<LogRecord>, AutoCloseable {
    private final Path outputPath;
    private BufferedWriter writer;
    public FileSink(Path outputPath) { this.outputPath = outputPath; }
    public void open() throws IOException { this.writer = new BufferedWriter(new FileWriter(outputPath.toFile())); }
    @Override public void consume(LogRecord record) throws IOException {
        if (record != null && writer != null) { writer.write(record.toString()); writer.newLine(); }
    }
    @Override public void close() throws IOException {
        if (writer != null) { writer.flush(); writer.close(); }
    }
}
