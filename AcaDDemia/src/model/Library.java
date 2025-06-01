package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<User> users;
    private List<Loan> loans;
    private BookDisplayService bookDisplayService;
    private FineCalculator fineCalculator;
    
    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.loans = new ArrayList<>();
        this.bookDisplayService = new BookDisplayService();
        this.fineCalculator = new FineCalculator();
    }

    public void addUser(UserDetails userDetails) {
        users.add(new User(userDetails.getName(), userDetails.getEmail(), userDetails.getPhone(), userDetails.getPassword()));
    }

    public void borrowBook(UserDetails userDetails, ISBN isbn, String message) {
        Book book = books.stream().filter(b -> b.getISBN().equals(isbn)).findFirst().orElse(null);
        User user = users.stream().filter(u -> u.getName().equals(userDetails.getName()) && u.getEmail().equals(userDetails.getEmail())).findFirst().orElse(null);
        if (book != null && user != null) {
            Notification notification = new Notification();
            Loan loan = new Loan(book, user, LocalDate.now(), notification);
            loans.add(loan);
            loan.processBorrowing(userDetails, message, fineCalculator);
        } else {
            System.out.println("Book or user not found");
        }
    }

    public void addBook(String isbnString, String title, String author) {
        ISBN isbn;
        try {
            if (isbnString.length() == 10) {
                isbn = new ISBN10(isbnString);
            } else if (isbnString.length() == 13) {
                isbn = new ISBN13(isbnString);
            } else {
                System.out.println("Invalid ISBN length (must be 10 or 13 digits)");
                return;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid ISBN: " + e.getMessage());
            return;
        }
        Book book = new Book(isbn, title, author);
        if (!book.isValidISBN()) {
            System.out.println("Invalid ISBN");
            return;
        }
        books.add(book);
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library");
        } else {
            for (Book book : books) {
                bookDisplayService.display(book, "full");
            }
        }
    }

    public void displayUsers() {
        if (users.isEmpty()) {
            System.out.println("No users in the library");
        } else {
            for (User user : users) {
                System.out.println("Name: " + user.getName() + ", Email: " + user.getEmail() + ", Phone: " + user.getPhone());
            }
        }
    }

    public void displayLoans() {
        if (loans.isEmpty()) {
            System.out.println("No loans in the library");
        } else {
            for (Loan loan : loans) {
                System.out.println("Book ISBN: " + loan.getBookIsbn() + ", User: " + loan.getUsername() + ", Borrow Date: " + loan.getBorrowDate());
            }
        }
    }

    private List<String> getAllNotifications() {
        List<String> allNotifications = new ArrayList<>();
        for (Loan loan : loans) {
            allNotifications.addAll(loan.getNotifications());
        }
        return allNotifications;
    }

    public void showUserNotifications(String email, String password) {
        User user = users.stream().filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password)).findFirst().orElse(null);
        if (user == null) {
            System.out.println("Invalid email or password");
            return;
        }
        List<String> userNotifications = getAllNotifications();
        if (userNotifications.isEmpty()) {
            System.out.println("No notifications for user: " + email);
        } else {
            for (String notification : userNotifications) {
                System.out.println(notification);
            }
        }
    }
		
    public List<User> getUsers() {
        return new ArrayList<>(users);
    }
}