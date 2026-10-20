import java.time.LocalDateTime;
public record LogRecord(LocalDateTime timestamp, String clientIp, String method, String path, int status, long responseSize) {}
