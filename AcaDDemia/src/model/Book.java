package model;

public class Book {
    public String isbn;
    public String title;
    public String author;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    public boolean isValidISBN() {
        // simplified, just check if it's 13 digits and starts with a valid prefix
        return isbn != null && isbn.matches("^97[89]\\d{10}$");
    }

    public void printDetails(String style) {
        if (style.equals("full")) {
            System.out.println("Title: " + title + ", Author: " + author + ", ISBN: " + isbn);
        } else if (style.equals("summary")) {
            System.out.println("Title: " + title);
        } else {
            System.out.println("Invalid style");
        }
    }

    public String getTitle() {
        return title;
    }
    
    public String getIsbn() {
    	return isbn;
    }
}