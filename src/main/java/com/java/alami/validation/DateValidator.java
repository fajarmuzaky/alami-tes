package com.java.alami.validation;

import java.text.ParseException;
import java.util.Date;

public interface DateValidator {
    boolean isValid(String dateStr);
    Date formatter(String dateStr) throws ParseException;
}
