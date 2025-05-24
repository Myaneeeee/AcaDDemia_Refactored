package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String email;
    private String phone;
    private String password;
    private List<Book> borrowedBooks;

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

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}