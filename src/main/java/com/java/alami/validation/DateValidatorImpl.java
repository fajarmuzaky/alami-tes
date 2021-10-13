package com.java.alami.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidatorImpl implements DateValidator {
    private String dateFormat;

    public DateValidatorImpl(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public boolean isValid(String dateStr) {
        DateFormat sdf = new SimpleDateFormat(this.dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public Date formatter(String dateStr) throws ParseException {
        DateFormat sdf = new SimpleDateFormat(this.dateFormat);
        return sdf.parse(dateStr);
    }
}
