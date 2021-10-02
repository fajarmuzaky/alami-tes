package com.java.alami.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class MemberFilterRequest {
    private Integer start;

    private Integer length;
}
