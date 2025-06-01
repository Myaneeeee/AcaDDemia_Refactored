package model;

public class BookDisplayService {
    public void display(Book book, String style) {
        if (style.equals("full")) {
            System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", ISBN: " + book.getISBN().getValue());
        } else if (style.equals("summary")) {
            System.out.println("Title: " + book.getTitle());
        } else {
            System.out.println("Invalid style");
        }
    }
}