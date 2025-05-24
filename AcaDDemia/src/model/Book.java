package model;

public class Book {
    private String isbn;
    private String title;
    private String author;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }
    
    // Note: Simplified ISBN validation for learning purpose only.
    public boolean isValidISBN() {
        if (isbn == null) {
            return false;
        }
        // ISBN-13: 13 digits, starts with 978 or 979
        if (isbn.matches("^97[89]\\d{10}$")) {
            return true;
        }
        // ISBN-10: 10 characters, last can be 'X', others digits
        if (isbn.length() == 10) {
            String firstNine = isbn.substring(0, 9);
            char lastChar = isbn.charAt(9);
            return firstNine.matches("\\d{9}") && (Character.isDigit(lastChar) || lastChar == 'X');
        }
        return false;
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
    
    public String getAuthor() {
    	return author;
    }
}