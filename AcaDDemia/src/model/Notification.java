package model;

import java.time.LocalDate;

public class Notification {
    public String sendOverdueNotification(String name, String email, String phone, String message, LocalDate borrowDate) {
        LocalDate today = LocalDate.now();
        long daysOverdue = today.toEpochDay() - borrowDate.toEpochDay();
        double fine = daysOverdue > 7 ? daysOverdue * 1.0 : 0.0;
        String formattedMessage = "Dear " + name + ", your loan is overdue. Fine: $" + fine + ". Contact: " + email + ", " + phone + " - " + message;
        System.out.println(formattedMessage);
        return formattedMessage;
    }
}