package model;

import java.time.LocalDate;

public class Notification {
    private final FineCalculator fineCalculator = new FineCalculator();

    public String sendOverdueNotification(String name, String email, String phone, String message, LocalDate borrowDate) {
        double fine = fineCalculator.calculateFine(borrowDate);
        String formattedMessage = "Dear " + name + ", your loan is overdue. Fine: $" + fine + ". Contact: " + email + ", " + phone + " - " + message;
        System.out.println(formattedMessage);
        return formattedMessage;
    }
}