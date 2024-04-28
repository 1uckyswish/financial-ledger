package com.pluralsight.UtilityMethods;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class UtilityMethods {

    public static int validateIntInput(Scanner scanner, String prompt) {
        return validateIntInput(scanner, prompt, false);
    }

    public static int validateIntInput(Scanner scanner, String prompt, boolean allowEmpty) {
        int input;
        while (true) {
            System.out.print(prompt);
            if (allowEmpty && scanner.hasNextLine() && scanner.nextLine().isEmpty()) {
                return 0; // Return 0 if input is empty and allowed
            }
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                break;
            } else {
                System.out.println("\n==== Sorry, that input is invalid. Please enter a valid numeric value ===\n");
                scanner.next(); // Consume invalid input
            }
        }
        return input;
    }

    public static String validateStringInput(Scanner scanner, String prompt) {
        return validateStringInput(scanner, prompt, false);
    }

    public static String validateStringInput(Scanner scanner, String prompt, boolean allowEmpty) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (allowEmpty || !input.isEmpty()) {
                break;
            }
            System.out.println("\n==== Sorry, that input is invalid. Please enter a valid non-empty string ===\n");
        }
        return input;
    }

    public static double validateDoubleInput(Scanner scanner, String prompt) {
        return validateDoubleInput(scanner, prompt, false);
    }

    public static double validateDoubleInput(Scanner scanner, String prompt, boolean allowEmpty) {
        double input;
        while (true) {
            System.out.print(prompt);
            if (allowEmpty && scanner.hasNextLine() && scanner.nextLine().isEmpty()) {
                return 0.0; // Return 0.0 if input is empty and allowed
            }
            if (scanner.hasNextDouble()) {
                input = scanner.nextDouble();
                break;
            } else {
                System.out.println("\n==== Sorry, that input is invalid. Please enter a valid numeric value ===\n");
                scanner.next(); // Consume invalid input
            }
        }
        return input;
    }

    public static String validateDateFormat(Scanner scanner, String prompt) {
        return validateDateFormat(scanner, prompt, false);
    }

    public static String validateDateFormat(Scanner scanner, String prompt, boolean allowEmpty) {
        String date;
        while (true) {
            System.out.print(prompt);
            date = scanner.nextLine().trim();
            if (allowEmpty && date.isEmpty()) {
                break;
            }
            if (isValidDateFormat(date)) {
                break;
            } else {
                System.out.print(
                        "\n==== Sorry, that input is invalid. Please enter a valid date in the format (YYYY-MM-DD) ===\n");
            }
        }
        return date;
    }

    private static boolean isValidDateFormat(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static String validateTimeFormat(Scanner scanner, String prompt) {
        return validateTimeFormat(scanner, prompt, false);
    }

    public static String validateTimeFormat(Scanner scanner, String prompt, boolean allowEmpty) {
        String time;
        while (true) {
            System.out.print(prompt);
            time = scanner.nextLine().trim();
            if (allowEmpty && time.isEmpty()) {
                break;
            }
            if (isValidTimeFormat(time)) {
                break;
            } else {
                System.out.println(
                        "\n==== Sorry, that input is invalid. Please enter a valid time in the format (HH:MM:SS) ===");
            }
        }
        return time;
    }

    private static boolean isValidTimeFormat(String time) {
        try {
            LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
