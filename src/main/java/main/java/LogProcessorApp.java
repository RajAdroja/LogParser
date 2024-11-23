package main.java;
import java.io.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LogProcessorApp {

    public static void main(String[] args) throws IOException {
        if (args.length < 2 || !args[0].equals("--file")) {
            System.out.println("Usage: --file <filename.txt>");
            return;
        }

        String filename = args[1];

        // Create handlers and chain them together
        APMLogHandler apmLogHandler = new APMLogHandler();
        ApplicationLogHandler appLogHandler = new ApplicationLogHandler();
        RequestLogHandler requestLogHandler = new RequestLogHandler();

        apmLogHandler.setNext(appLogHandler);
        appLogHandler.setNext(requestLogHandler);

        // Read log file and process each line
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                apmLogHandler.handle(line); // Start with the first handler in the chain
            }
        }

        // Write JSON output files using Jackson
        ObjectMapper mapper = new ObjectMapper();

        // APM Logs output
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("apm.json"), apmLogHandler.getAggregatedMetrics());

        // Application Logs output
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("application.json"), appLogHandler.getSeverityCounts());

        // Request Logs output
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("request.json"), requestLogHandler.getAggregatedRequestLogs());

        System.out.println("Logs processed and JSON files generated.");
    }
}

// mvn exec:java -Dexec.args="--file src/main/resources/input.txt"