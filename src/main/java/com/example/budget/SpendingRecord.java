package com.example.budget;

import java.time.LocalDate;

public class SpendingRecord {
    private LocalDate date;
    private String category;
    private String description;
    private double amount;

    public SpendingRecord(LocalDate date, String category, String description, double amount) {
        this.date = date;
        this.category = category;
        this.description = description;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | $%.2f", date, category, description, amount);
    }
}
