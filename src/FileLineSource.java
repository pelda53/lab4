import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
public class FileLineSource implements Source<String> {
    private final BufferedReader reader;
    public FileLineSource(Path path) throws IOException { this.reader = Files.newBufferedReader(path); }
    @Override public String next() throws IOException { return reader.readLine(); }
}
