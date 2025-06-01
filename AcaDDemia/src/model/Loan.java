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

    public void processBorrowing(UserDetails userDetails, String message, FineCalculator fineCalculator) {
        if (!checkBookValidity()) {
            return;
        }
        borrow();
        double fine = calculateFine(fineCalculator);
        if (fine > 0) {
            checkUserFine(fineCalculator);
        }
        notifyUser(userDetails, message);
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

     private void borrow() {
        user.borrowBook(book);
    }

    private void notifyUser(UserDetails userDetails, String message) {
        String notificationMessage = notification.sendOverdueNotification(
            userDetails.getName(),
            userDetails.getEmail(),
            userDetails.getPhone(),
            message,
            borrowDate
        );
        notifications.add(notificationMessage);
    }

    public String getBookIsbn() {
        return book.getISBN().getValue();
    }

    public String getUsername() {
        return user.getName();
    }
}