package com.example.budget;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonExporter {

    public String exportToJson(List<SpendingRecord> records) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd")
                .create();

        // Calculate summary statistics
        double totalSpending = records.stream()
                .mapToDouble(SpendingRecord::getAmount)
                .sum();

        // Calculate category totals
        Map<String, Double> categoryTotals = new HashMap<>();
        for (SpendingRecord record : records) {
            categoryTotals.merge(record.getCategory(), record.getAmount(), Double::sum);
        }

        // Calculate monthly totals
        Map<String, Double> monthlyTotals = new HashMap<>();
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMM yyyy");
        for (SpendingRecord record : records) {
            YearMonth month = YearMonth.from(record.getDate());
            String monthKey = month.format(monthFormatter);
            monthlyTotals.merge(monthKey, record.getAmount(), Double::sum);
        }

        // Build JSON structure
        JsonObject root = new JsonObject();
        
        // Summary section
        JsonObject summary = new JsonObject();
        summary.addProperty("total_spending", totalSpending);
        summary.addProperty("transaction_count", records.size());
        root.add("summary", summary);

        // Category breakdown
        root.add("spending_by_category", gson.toJsonTree(categoryTotals));

        // Monthly breakdown
        root.add("spending_by_month", gson.toJsonTree(monthlyTotals));

        // All transactions
        root.add("transactions", gson.toJsonTree(records));

        return gson.toJson(root);
    }
}
