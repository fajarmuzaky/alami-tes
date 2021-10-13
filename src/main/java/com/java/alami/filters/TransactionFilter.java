package com.java.alami.filters;

import lombok.Data;
import org.springframework.data.relational.core.sql.In;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;
import java.util.Date;

@Data
public class TransactionFilter implements BaseFilter {

    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date startDate;

    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date endDate;
}
