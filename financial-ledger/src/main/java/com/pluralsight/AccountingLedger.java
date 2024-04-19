package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AccountingLedger {
    static ArrayList<Transaction> transactionHistory = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        readAndAddToTransactionHistory();
        displayHomeScreen(scanner);
    }

    public static void displayHomeScreen(Scanner scanner) throws IOException {
        System.out.println("------------------------------------------------------------");
        System.out.println("\t\t Welcome to the Account Ledger Application");
        System.out.println("\t\t - Your Ultimate Finance Tracker in Java! - \t");
        System.out.println("------------------------------------------------------------\n");
        System.out.println("Please select from the following options:");
        System.out.println("(D) Add Deposit - Add a deposit to the ledger");
        System.out.println("(P) Make Payment (Debit) - Make a payment and deduct it from the ledger");
        System.out.println("(L) Ledger - Display the ledger");
        System.out.println("(X) Exit - Exit the application");
        // Prompt user for input
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine().toUpperCase();

        // Handle user's choice
        switch (choice) {
            case "D":
                // Handle Add Deposit option
                // Call method to add deposit
                addTransaction(scanner, true);
                break;
            case "P":
                // Handle Make Payment option
                // Call method to make payment
                addTransaction(scanner, false);
                break;
            case "L":
                // Handle Ledger option
                // Call method to display ledger
                displayLedger(scanner);
                break;
            case"N":
                getNetworth();
                break;
            case "X":
                // Handle Exit option
                System.out.println("Exiting the application...");
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
                displayHomeScreen(scanner);
        }
        scanner.close();
    }

    public static void displayLedger(Scanner scanner) throws IOException {
        System.out.println("\nLedger Options:");
        System.out.println("(A) Show All - Display all transaction entries");
        System.out.println("(D) Deposits Only - Display only deposit transactions");
        System.out.println("(P) Payments Only - Display only negative transactions (payments)");
        System.out.println("(R) Run Reports - Open a new screen to run predefined transaction reports");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine().trim().toUpperCase();

        switch (choice) {
            case "A":
                displayTransactions("All", scanner);
                break;
            case "D":
                displayTransactions("Deposits", scanner);
                break;
            case "P":
                displayTransactions("Payments", scanner);
                break;
            case "R":
                // Call method to handle running reports
                displayReports(scanner);
                break;
            default:
                System.out.println("Invalid choice. Please choose a valid option (A, D, P, or R).");
                displayLedger(scanner);
        }

    }

    public static void displayReports(Scanner scanner) throws IOException {
        System.out.println("\nReports - Access pre-defined reports or run custom searches:");
        System.out.println("(1) Month To Date");
        System.out.println("(2) Previous Month");
        System.out.println("(3) Year To Date");
        System.out.println("(4) Previous Year");
        System.out.println("(5) Search by Vendor - Enter the vendor name to display all entries for that vendor");
        System.out.println("(6) Custom Search - Enter any input for filtered search");
        System.out.println("(7) Back - Return to the report page");
        System.out.println("(8) Home - Return to the home page");

        // Prompt user for choice
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                // Handle Month To Date
                break;
            case "2":
                // Handle Previous Month
                break;
            case "3":
                // Handle Year To Date
                break;
            case "4":
                // Handle Previous Year
                break;
            case "5":
                // Handle Search by Vendor
                break;
            case "6":
                // Handle Custom Search
//                customReportSearch(scanner);
                break;
            case "7":
                // Handle Back
                displayLedger(scanner);
                break;
            case "8":
                // Handle Home
                displayHomeScreen(scanner);
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 8.");
                // Re-display the reports menu
                displayReports(scanner);
        }
    }

    public static void displayTransactions(String displayOption, Scanner scanner) throws IOException {
        System.out.println("Transactions (" + displayOption + "):");
        for (Transaction transaction : transactionHistory) {
            switch (displayOption.toLowerCase()) {
                case "all":
                    System.out.println(transaction);
                    break;
                case "deposits":
                    if (transaction.getAmount() > 0) {
                        System.out.println(transaction);
                    }
                    break;
                case "payments":
                    if (transaction.getAmount() < 0) {
                        System.out.println(transaction);
                    }
                    break;
                default:
                    System.out.println("Invalid display option");
                    return;
            }
        }

        while (true) {
            System.out.println("\nPlease select your next action:");
            System.out.println("1. Go back home");
            System.out.println("2. View other ledger reports");
            System.out.print("Enter your choice: ");
            String userChoice = scanner.nextLine();
            switch (userChoice) {
                case "1":
                    // Go back home
                    displayHomeScreen(scanner);
                    return; // Break out of the loop
                case "2":
                    // View other ledger reports
                    displayLedger(scanner);
                    return; // Break out of the loop
                default:
                    System.out.println("Invalid choice. Please choose 1 or 2.");
            }
        }
    }

    public static void addTransaction(Scanner scanner, boolean isDeposit) throws IOException {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\t   - Please Fill out the following information - \t");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        // Ask the user if they want to use the current date
        System.out.print("Do you want to use the current date? (Y/N): ");
        String choice = scanner.nextLine().toUpperCase();
        String date;

        if (choice.equals("Y")) {
            date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else {
            System.out.print("Please enter the date (YYYY-MM-DD): ");
            date = scanner.nextLine();
        }

        // Ask the user if they want to use the current time
        System.out.print("Do you want to use the current time? (Y/N): ");
        choice = scanner.nextLine().toUpperCase();
        LocalTime currentTime = LocalTime.now();
        String time;
        if (choice.equals("Y")) {
            time = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        } else {
            // Ask the user to enter a specific time
            System.out.print("Please enter the time (HH:MM:SS): ");
            time = scanner.nextLine();
        }

        System.out.print("Please enter the description: ");
        String description = scanner.nextLine();

        System.out.print("Please enter the vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Please enter the amount: ");
        double amount = scanner.nextDouble();

        // Convert positive amount to negative if it's a payment
        if (!isDeposit && amount > 0) {
            amount = -amount;
        }

        scanner.nextLine(); // Consume the newline character left by nextDouble()

        System.out.println("- Summary of " + (isDeposit ? "Deposit" : "Payment") + " -");
        // Create a new transaction
        Transaction newTransaction = new Transaction(date, time, description, vendor, amount);
        // Add the new transaction to the CSV file
        addTransactionToCSV(date, time, description, vendor, amount);
        // Add the new transaction to the transactionHistory ArrayList immediately
        transactionHistory.add(newTransaction);
        System.out.println(newTransaction);
        // Provide feedback to the user
        System.out.println((isDeposit ? "Deposit" : "Payment") + " added successfully!");

        // Return to the home screen
        goToHomeScreen(scanner);
    }

    public static void addTransactionToCSV(String date, String time, String description, String vendor, double amount)
            throws IOException {
        File file = new File("transactions.csv");
        boolean fileExists = file.exists();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        // If the file doesn't exist, write the header
        if (!fileExists) {
            writer.write("date|time|description|vendor|amount\n"); // Modify this line with your header
        }
        // Write transaction data
        // Example transaction data:
        writer.write(String.format("%s|%s|%s|%s|%.2f%n", date, time, description, vendor, amount));
        // Close the writer
        writer.close();
    }

    public static void readAndAddToTransactionHistory() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));
        reader.readLine();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] transactionsSplit = line.split(Pattern.quote("|"));
            transactionHistory.add(createTransactionFromCsv(transactionsSplit));
        }
        reader.close();
    }

    public static Transaction createTransactionFromCsv(String[] csvTransactions) {
        String transactionDate = csvTransactions[0];
        String transactionTime = csvTransactions[1];
        String transactionDescription = csvTransactions[2];
        String transactionVendor = csvTransactions[3];
        double transactionAmount = Double.parseDouble(csvTransactions[4]);
        return new Transaction(transactionDate, transactionTime, transactionDescription, transactionVendor,
                transactionAmount);
    }

    public static void goToHomeScreen(Scanner scanner) throws IOException {
        while (true) {
            System.out.println("\nPlease select your next action:");
            System.out.println("1. Go back home");
            System.out.println("2. View ledger reports");
            System.out.println("3. Exit Program");
            System.out.print("Enter your choice: ");
            String userChoice = scanner.nextLine();
            switch (userChoice) {
                case "1":
                    // Go back home
                    displayHomeScreen(scanner);
                    return; // Break out of the loop
                case "2":
                    // View other ledger reports
                    displayLedger(scanner);
                    return; // Break out of the loop
                case "3":
                    // Exit
                    System.out.println("Exiting....");
                    return; // Break out of the loop
                default:
                    System.out.println("Invalid choice. Please choose 1 or 2.");
            }
        }
    }

    public static void getNetworth() {
        double totalPositive = 0;
        double totalNegative = 0;

        for(Transaction transaction : transactionHistory){
            if(transaction.getAmount() > 0){
                totalPositive += transaction.getAmount();
            }else{
                totalNegative += transaction.getAmount();
            }
        }

        System.out.println("Total Income: " + totalPositive);
        System.out.println("Total Expenses: " + (- totalNegative));
        double netWorth = totalPositive + totalNegative;
        System.out.println("Net Worth: " + netWorth);
    }
}
