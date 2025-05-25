package model;

public abstract class ISBN {
    protected String value;

    protected ISBN(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public abstract boolean isValid();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ISBN)) return false;
        ISBN other = (ISBN) obj;
        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}