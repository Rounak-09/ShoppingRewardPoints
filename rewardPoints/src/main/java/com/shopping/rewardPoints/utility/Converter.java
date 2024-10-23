package com.shopping.rewardPoints.utility;

import com.shopping.rewardPoints.exception.UserException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public final class Converter {

    private Converter() {
    }

    public static LocalDate getLocalDateFromString(String date) {
        if (date == null || date.trim().isEmpty()) return null;
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (Exception e) {
            throw new UserException("Invalid date format!");
        }
    }
}
