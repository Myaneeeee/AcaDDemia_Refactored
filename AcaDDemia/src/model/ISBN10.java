package model;

public class ISBN10 extends ISBN {
    public ISBN10(String value) {
        super(value);
        if (!isValid()) {
            throw new IllegalArgumentException("Invalid ISBN-10 format");
        }
    }

    @Override
    public boolean isValid() {
        if (value == null) {
            return false;
        }
        if (value.length() == 10) {
            String firstNine = value.substring(0, 9);
            char lastChar = value.charAt(9);
            return firstNine.matches("\\d{9}") && (Character.isDigit(lastChar) || lastChar == 'X');
        }
        return false;
    }
}