package com.project.automaxn.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class App {

    static LocalDate today = LocalDate.now();          // Get today's date
    static DateTimeFormatter formatter;

    public String getTodaysDTS() {
        // Define the desired format
        formatter = DateTimeFormatter.ofPattern("ddMMyy");

        // Format the date
        return today.format(formatter);
    }

    public static String getTodaysDate() {
        // Define the desired format
        formatter = DateTimeFormatter.ofPattern("dd");

        // Format the date
        return today.format(formatter);
    }

    public static String getTodaysMonth() {
        // Define the desired format
        formatter = DateTimeFormatter.ofPattern("MM");

        // Format the date
        return today.format(formatter);
    }

    public static String getTodaysYear() {
        // Define the desired format
        formatter = DateTimeFormatter.ofPattern("yy");

        // Format the date
        return today.format(formatter);
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");

        // WebDriver driver = new ChromeDriver();
        // driver = DriverManager.getDriver();
        // String url = ConfigReader.getProperty("baseUrl");
        // System.out.println("URL: " + url);
        // String username = JsonConfigReader.getCredential("admin", "username");
        // System.out.println("Username: " + username);
        // String password = JsonConfigReader.getCredential(ConfigReader.getProperty("role"), "password");
        String day = getTodaysDate();
        String month = getTodaysMonth();
        String year = getTodaysYear();

        System.out.println("MM/DD/YY format: " + month + "/" + day + "/" + Integer.toString(Integer.parseInt(year) - 10));
    }
}
