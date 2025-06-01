package app;

import java.util.Scanner;

import model.ISBN;
import model.ISBN10;
import model.ISBN13;
import model.Library;
import model.UserDetails;

public class AppManager {
    private Library library;
    private Scanner scanner;

    public AppManager() {
        this.library = new Library();
        this.scanner = new Scanner(System.in);
    }

    private void printMenu() {
        System.out.println("\nAcaDDemia Library Management System");
        System.out.println("1. Add a Book");
        System.out.println("2. Add a User");
        System.out.println("3. Borrow a Book");
        System.out.println("4. Display All Books");
        System.out.println("5. Display All Users");
        System.out.println("6. Display All Loans");
        System.out.println("7. Show All Notifications");
        System.out.println("8. Exit");
        System.out.print("Enter choice (1-8): ");
    }

    private int getUserChoice() {
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
        return choice;
    }

    private void addBook() {
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        library.addBook(isbn, title, author);
        System.out.println("Book added successfully");
    }

    private void addUser() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        UserDetails userDetails = new UserDetails(name, email, phone, password);
        library.addUser(userDetails);
        System.out.println("User added successfully");
    }

    private void borrowBook() {
        System.out.print("Enter User Name: ");
        String borrowName = scanner.nextLine();
        System.out.print("Enter User Email: ");
        String borrowEmail = scanner.nextLine();
        System.out.print("Enter User Phone: ");
        String borrowPhone = scanner.nextLine();
        System.out.print("Enter Book ISBN: ");
        String borrowIsbn = scanner.nextLine();
        ISBN borrowIsbnObj;
        try {
            if (borrowIsbn.length() == 10) {
                borrowIsbnObj = new ISBN10(borrowIsbn);
            } else if (borrowIsbn.length() == 13) {
                borrowIsbnObj = new ISBN13(borrowIsbn);
            } else {
                System.out.println("Invalid ISBN length (must be 10 or 13 digits)");
                return;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid ISBN: " + e.getMessage());
            return;
        }
        System.out.print("Enter Notification Message: ");
        String message = scanner.nextLine();
        UserDetails borrowUserDetails = new UserDetails(borrowName, borrowEmail, borrowPhone, "");
        library.borrowBook(borrowUserDetails, borrowIsbnObj, message);
    }

    private void getUserNotifications() {
        System.out.print("Enter User Email: ");
        String userEmail = scanner.nextLine();
        System.out.print("Enter Password: ");
        String userPassword = scanner.nextLine();
        library.showUserNotifications(userEmail, userPassword);
    }

    public void runMenu() {
        while (true) {
            printMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    addUser();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    library.displayBooks();
                    break;
                case 5:
                    library.displayUsers();
                    break;
                case 6:
                    library.displayLoans();
                    break;
                case 7:
                    getUserNotifications();
                    break;
                case 8:
                    System.out.println("Exiting AcaDDemia. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 1-8.");
            }
        }
    }
}