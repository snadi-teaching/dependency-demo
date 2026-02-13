package com.example.budget;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<SpendingRecord> readSpendingRecords(String filePath) throws IOException {
        List<SpendingRecord> records = new ArrayList<>();

        try (FileReader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .withFirstRecordAsHeader()
                     .withIgnoreHeaderCase()
                     .withTrim())) {

            for (CSVRecord csvRecord : csvParser) {
                long rowNumber = csvRecord.getRecordNumber();
                try {
                    // Validate required columns exist
                    if (!csvRecord.isMapped("date") || !csvRecord.isMapped("category") || 
                        !csvRecord.isMapped("description") || !csvRecord.isMapped("amount")) {
                        throw new IllegalArgumentException(
                            "CSV file must contain columns: date, category, description, amount");
                    }

                    LocalDate date = LocalDate.parse(csvRecord.get("date"), DATE_FORMATTER);
                    String category = csvRecord.get("category");
                    String description = csvRecord.get("description");
                    double amount = Double.parseDouble(csvRecord.get("amount"));

                    records.add(new SpendingRecord(date, category, description, amount));
                } catch (Exception e) {
                    throw new IOException(String.format(
                        "Error parsing row %d: %s", rowNumber, e.getMessage()), e);
                }
            }
        }

        return records;
    }
}
