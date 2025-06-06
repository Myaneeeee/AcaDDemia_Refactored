package model;

import java.time.LocalDate;

public class Notification {
    public String sendOverdueNotification(String name, String email, String phone, String message, LocalDate borrowDate, double fine) {
        String formattedMessage = "Dear " + name + ", your loan is overdue. Fine: $" + fine + ". Contact: " + email + ", " + phone + " - " + message;
        System.out.println(formattedMessage);
        return formattedMessage;
    }
}

