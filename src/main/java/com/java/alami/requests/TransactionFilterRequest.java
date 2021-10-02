package com.java.alami.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class TransactionFilterRequest {
    private Integer start;

    private Integer length;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date start_of_date;

    @JsonFormat(pattern="dd-MM-yyyy")
    private Date end_of_date;
}
