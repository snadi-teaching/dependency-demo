package com.example.budget;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportGenerator {

    public String generateReport(List<SpendingRecord> records) {
        if (records.isEmpty()) {
            return "No spending records found.";
        }

        StringBuilder report = new StringBuilder();
        report.append("\n========== SPENDING REPORT ==========\n\n");

        // Calculate total spending
        double totalSpending = 0;
        Map<String, Double> categoryTotals = new HashMap<>();

        for (SpendingRecord record : records) {
            totalSpending += record.getAmount();
            categoryTotals.merge(record.getCategory(), record.getAmount(), Double::sum);
        }

        // Display summary
        report.append("Total Spending: $").append(String.format("%.2f", totalSpending)).append("\n");
        report.append("Number of Transactions: ").append(records.size()).append("\n\n");

        // Display spending by category
        report.append("Spending by Category:\n");
        report.append("--------------------\n");
        for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
            double percentage = (entry.getValue() / totalSpending) * 100;
            report.append(String.format("%-15s: $%8.2f (%5.1f%%)\n", 
                    entry.getKey(), entry.getValue(), percentage));
        }

        report.append("\n");
        report.append("Recent Transactions:\n");
        report.append("--------------------\n");
        
        // Display up to 10 most recent transactions
        int displayCount = Math.min(10, records.size());
        for (int i = records.size() - displayCount; i < records.size(); i++) {
            report.append(records.get(i).toString()).append("\n");
        }

        report.append("\n=====================================\n");

        return report.toString();
    }
}
