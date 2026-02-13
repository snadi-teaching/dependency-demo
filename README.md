## Simple Budget Application

A basic Maven-based Java application that allows users to upload CSV files containing spending records and generate spending reports.

### Features
- Parse CSV files with spending data
- Calculate total spending
- Break down spending by category
- Show percentage distribution per category
- Display recent transactions

### Prerequisites
- Java 11 or higher
- Maven 3.6 or higher

### Building the Application

To build the application, run:

```bash
mvn clean package
```

This will create a JAR file with all dependencies included in the `target` directory.

### Usage

Run the application with a CSV file:

```bash
java -jar target/budget-app-1.0-SNAPSHOT-jar-with-dependencies.jar <path-to-csv-file>
```

Example:
```bash
java -jar target/budget-app-1.0-SNAPSHOT-jar-with-dependencies.jar sample-spending.csv
```

### CSV File Format

The CSV file should have the following columns:
- `date` - Date in YYYY-MM-DD format (e.g., 2024-01-15)
- `category` - Spending category (e.g., Food, Transportation, Entertainment)
- `description` - Brief description of the transaction
- `amount` - Amount spent (decimal number)

Example CSV:
```csv
date,category,description,amount
2024-01-05,Food,Grocery shopping,85.50
2024-01-07,Transportation,Gas station,45.00
2024-01-10,Entertainment,Movie tickets,30.00
```

A sample CSV file (`sample-spending.csv`) is included in the repository for testing.

### Project Structure
```
├── pom.xml                 # Maven configuration
├── sample-spending.csv     # Sample CSV file for testing
└── src/
    └── main/
        └── java/
            └── com/
                └── example/
                    └── budget/
                        ├── BudgetApp.java          # Main application class
                        ├── CSVReader.java          # CSV file parser
                        ├── ReportGenerator.java    # Report generation logic
                        └── SpendingRecord.java     # Data model for spending records
```

### Dependencies
- Apache Commons CSV 1.10.0 - For parsing CSV files
- JUnit 4.13.2 - For unit testing (test scope)
