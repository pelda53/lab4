import java.nio.file.Paths;
public class Main {
    public static void main(String[] args) throws Exception {
        int minStatus = 0;
        String pathPrefix = null;
        String[] fields = null;
        String inputFile = "input.log";
        String outputFile = "output.log";
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--min-status" -> minStatus = Integer.parseInt(args[++i]);
                case "--path-prefix" -> pathPrefix = args[++i];
                case "--fields" -> fields = args[++i].split(",");
                case "--in" -> inputFile = args[++i];
                case "--out" -> outputFile = args[++i];
            }
        }
        PipelineBuilder builder = PipelineBuilder.from(new FileLineSource(Paths.get(inputFile)))
                .via(new ParserStage());
        if (minStatus > 0) {
            final int statusCutoff = minStatus;
            builder.via(new PredicateFilterStage(r -> r.status() >= statusCutoff));
        }
        if (pathPrefix != null) {
            final String prefix = pathPrefix;
            builder.via(new PredicateFilterStage(r -> r.path() != null && r.path().startsWith(prefix)));
        }
        if (fields != null && fields.length > 0) {
            builder.via(new ProjectionStage(fields));
        }
        Pipeline pipeline = builder.to(new FileSink(Paths.get(outputFile)));
        pipeline.execute();
        System.out.println("Pipeline executed successfully. Output written to " + outputFile);
    }
}
