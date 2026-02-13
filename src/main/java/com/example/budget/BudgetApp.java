package com.example.budget;

import java.io.IOException;
import java.util.List;

public class BudgetApp {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java -jar budget-app.jar <csv-file-path> [--json]");
            System.out.println("Example: java -jar budget-app.jar spending.csv");
            System.out.println("         java -jar budget-app.jar spending.csv --json");
            System.out.println();
            System.out.println("Options:");
            System.out.println("  --json    Export data in JSON format instead of text report");
            System.exit(1);
        }

        String csvFilePath = args[0];
        boolean jsonOutput = false;
        
        // Check for --json flag
        if (args.length > 1 && "--json".equals(args[1])) {
            jsonOutput = true;
        }
        
        try {
            // Read CSV file
            CSVReader csvReader = new CSVReader();
            List<SpendingRecord> records = csvReader.readSpendingRecords(csvFilePath);
            
            if (jsonOutput) {
                // Export as JSON
                JsonExporter jsonExporter = new JsonExporter();
                String json = jsonExporter.exportToJson(records);
                System.out.println(json);
            } else {
                // Generate and display text report
                ReportGenerator reportGenerator = new ReportGenerator();
                String report = reportGenerator.generateReport(records);
                System.out.println(report);
            }
            
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Error processing data: " + e.getMessage());
            System.exit(1);
        }
    }
}
