package app;

import java.util.Scanner;
import model.Library;

public class AppManager {
    private Library library;
    private Scanner scanner;

    public AppManager() {
        this.library = new Library();
        this.scanner = new Scanner(System.in);
    }

    public void runMenu() {
        while (true) {
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

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    // Add a Book
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    library.addBook(isbn, title, author);
                    System.out.println("Book added successfully");
                    break;
                case 2:
                    // Add a User
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();
                    library.addUser(name, email, phone, password);
                    System.out.println("User added successfully");
                    break;
                case 3:
                    // Borrow a Book
                    System.out.print("Enter User Name: ");
                    String borrowName = scanner.nextLine();
                    System.out.print("Enter User Email: ");
                    String borrowEmail = scanner.nextLine();
                    System.out.print("Enter User Phone: ");
                    String borrowPhone = scanner.nextLine();
                    System.out.print("Enter Book ISBN: ");
                    String borrowIsbn = scanner.nextLine();
                    System.out.print("Enter Notification Message: ");
                    String message = scanner.nextLine();
                    library.borrowBook(borrowName, borrowEmail, borrowPhone, borrowIsbn, message);
                    break;
                case 4:
                    // Display All Books
                    library.displayBooks();
                    break;
                case 5:
                    // Display All Users
                    library.displayUsers();
                    break;
                case 6:
                    // Display All Loans
                    library.displayLoans();
                    break;
                case 7:
                    // Show All Notifications
                    System.out.print("Enter User Email: ");
                    String userEmail = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String userPassword = scanner.nextLine();
                    library.showUserNotifications(userEmail, userPassword);
                    break;
                case 8:
                    // Exit
                    System.out.println("Exiting AcaDDemia. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 1-8.");
            }
        }
    }
}