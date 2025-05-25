package model;

public class Book {
    private ISBN isbn;
    private String title;
    private String author;

    public Book(ISBN isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }
    
    public Book(Book other) {
        this.isbn = other.isbn;
        this.title = other.title;
        this.author = other.author;
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
    
    public String getAuthor() {
    	return author;
    }

    public ISBN getISBN() {
    	return isbn;
    }

    public boolean isValidISBN() {
        return isbn.isValid();
    }
}