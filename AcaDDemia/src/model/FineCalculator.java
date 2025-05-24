package model;

import java.time.LocalDate;

public class FineCalculator {
    public double calculateFine(LocalDate borrowDate) {
        LocalDate today = LocalDate.now();
        long daysOverdue = today.toEpochDay() - borrowDate.toEpochDay();
        return daysOverdue > 7 ? daysOverdue * 1.0 : 0.0;
    }
}