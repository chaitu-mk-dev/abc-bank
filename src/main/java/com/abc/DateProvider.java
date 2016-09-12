package com.abc;

import java.time.LocalDate;

public class DateProvider {
    private static DateProvider instance = null;

    public static LocalDate now() {
        return LocalDate.now();
    }
}
