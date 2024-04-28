package br.edu.umfg.order.validator;

import java.time.LocalDate;

public class Preconditions {

    public static void checkNotBlank(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void checkNotNull(Object value, String message) {
        if (value == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void checkSize(String value, int minSize, int maxSize, String message) {
        if (value != null && value.length() >= minSize && value.length() <= maxSize) {
            return;
        }
        throw new IllegalArgumentException(message);
    }

    public static void checkSize(String value, int size, String message) {
        if (value != null && value.length() == size) {
            return;
        }
        throw new IllegalArgumentException(message);
    }

    public static void checkAge(LocalDate date, int ageExpected, String message) {
        if (date == null || LocalDate.now().getYear() - date.getYear() < ageExpected) {
            throw new IllegalArgumentException(message);
        }
    }

}
