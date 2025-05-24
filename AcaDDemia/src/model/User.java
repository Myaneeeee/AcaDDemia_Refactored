package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    public String name;
    public String email;
    public String phone;
    public String password;
    public List<Book> borrowedBooks;

    public User(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.borrowedBooks = new ArrayList<>();
    }
    
    public List<Book> getBorrowedBooks() {
	    List<Book> deepCopy = new ArrayList<>();
	    for (Book b : borrowedBooks) {
	        deepCopy.add(new Book(b));
	    }
	    return deepCopy;
		}

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }
}