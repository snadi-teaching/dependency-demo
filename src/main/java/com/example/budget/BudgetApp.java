package com.example.budget;

import java.io.IOException;
import java.util.List;

public class BudgetApp {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java -jar budget-app.jar <csv-file-path>");
            System.out.println("Example: java -jar budget-app.jar spending.csv");
            System.exit(1);
        }

        String csvFilePath = args[0];
        
        try {
            // Read CSV file
            CSVReader csvReader = new CSVReader();
            List<SpendingRecord> records = csvReader.readSpendingRecords(csvFilePath);
            
            // Generate and display report
            ReportGenerator reportGenerator = new ReportGenerator();
            String report = reportGenerator.generateReport(records);
            System.out.println(report);
            
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Error processing data: " + e.getMessage());
            System.exit(1);
        }
    }
}
