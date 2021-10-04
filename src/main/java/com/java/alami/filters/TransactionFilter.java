package com.java.alami.filters;

import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import java.util.Calendar;
import java.util.Date;

@Data
public class TransactionFilter implements BaseFilter {
    private Date startDate;
    private Date endDate;
}
