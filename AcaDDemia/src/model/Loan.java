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

    private boolean checkBookValidity() {
        if (book == null) {
            System.out.println("Book not available");
            return false;
        }
        if (!book.isValidISBN()) {
            System.out.println("Invalid ISBN");
            return false;
        }
        return true;
    }

    private double checkUserFine(FineCalculator fineCalculator) {
        double fine = calculateFine(fineCalculator);
        if (fine > 0) {
            System.out.println("Fine for " + user.getName() + ": $" + fine);
        }
        return fine;
    }

    public void processBorrowing(String name, String email, String phone, String message, FineCalculator fineCalculator) {
        if (!checkBookValidity()) {
            return;
        }
        user.getBorrowedBooks().add(book);

        double fine  = checkUserFine(fineCalculator);
        notification.sendOverdueNotification(name, email, phone, message, borrowDate);
        
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

    public String getUserEmail() {
        return user.getEmail();
    }
}