package model;

public class ISBN13 extends ISBN {
    public ISBN13(String value) {
        super(value);
        if (!isValid()) {
            throw new IllegalArgumentException("Invalid ISBN-13 format");
        }
    }

    @Override
    public boolean isValid() {
        if (value == null) {
            return false;
        }
        return value.matches("^97[89]\\d{10}$");
    }
}