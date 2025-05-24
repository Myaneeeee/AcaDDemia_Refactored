package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Loan {
    private Book book;
    private User user;
    private LocalDate borrowDate;
    private Notification notification;
    private List<String> notifications;

    public Loan(Book book, User user, LocalDate borrowDate, Notification notification) {
        this.book = book;
        this.user = user;
        this.borrowDate = borrowDate;
        this.notification = notification;
        this.notifications = new ArrayList<>();
    }

    public void processBorrowing(String name, String email, String phone, String message, FineCalculator fineCalculator) {
        // Check if book is available
        if (book == null) {
            System.out.println("Book not available");
            return;
        }
        // Validate ISBN of the book
        if (!book.isValidISBN()) {
            System.out.println("Invalid ISBN");
            return;
        }
        // Update user's borrowed books list
        user.borrowedBooks.add(book);
        // Calculate overdue fines for the loan
        double fine = calculateFine(fineCalculator);
        // Print fine if applicable
        if (fine > 0) {
            System.out.println("Fine for " + user.name + ": $" + fine);
        }
        // Send notification to user
        notification.sendOverdueNotification(name, email, phone, message, borrowDate);
        // Store notification
        String notificationMessage = "Dear " + name + ", your loan is overdue. Fine: $" + fine + ". Contact: " + email + ", " + phone + " - " + message;
        notifications.add(notificationMessage);
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public List<String> getNotifications() {
        return notifications;
    }
    
    private double calculateFine(FineCalculator fineCalculator) {
        return fineCalculator.calculateFine(borrowDate);
    }
}