package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Loan> loans = new ArrayList<>();

    public void addUser(String name, String email, String phone, String password) {
        users.add(new User(name, email, phone, password));
    }

    public void borrowBook(String name, String email, String phone, String isbn, String message) {
        Book book = books.stream().filter(b -> b.isbn.equals(isbn)).findFirst().orElse(null);
        User user = users.stream().filter(u -> u.name.equals(name) && u.email.equals(email)).findFirst().orElse(null);
        if (book != null && user != null) {
            Notification notification = new Notification();
            Loan loan = new Loan(book, user, LocalDate.now(), notification);
            loans.add(loan);
            loan.processBorrowing(name, email, phone, message);
        } else {
            System.out.println("Book or user not found");
        }
    }

    public void addBook(String isbn, String title, String author) {
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
                book.printDetails("full");
            }
        }
    }

    public void displayUsers() {
        if (users.isEmpty()) {
            System.out.println("No users in the library");
        } else {
            for (User user : users) {
                System.out.println("Name: " + user.name + ", Email: " + user.email + ", Phone: " + user.phone);
            }
        }
    }

    public void displayLoans() {
        if (loans.isEmpty()) {
            System.out.println("No loans in the library");
        } else {
            for (Loan loan : loans) {
                System.out.println("Book ISBN: " + loan.getBook().getIsbn() + ", User: " + loan.getUser().name + ", Borrow Date: " + loan.getBorrowDate());
            }
        }
    }

    public void showUserNotifications(String email, String password) {
        User user = users.stream().filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password)).findFirst().orElse(null);
        if (user == null) {
            System.out.println("Invalid email or password");
            return;
        }
        List<String> userNotifications = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.getUserEmail().equals(email)) {
                userNotifications.addAll(loan.getNotifications());
            }
        }
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