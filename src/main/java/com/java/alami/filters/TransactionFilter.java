package com.java.alami.filters;

import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import java.util.Calendar;
import java.util.Date;

@Data
public class TransactionFilter implements BaseFilter {
    private Date startDate;
    private Date endDate;

//    public Date getEndDate() {
//        if(this.endDate==null){
//            return null;
//        }
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(this.endDate);
//        calendar.set(Calendar.HOUR_OF_DAY, 23);
//        calendar.set(Calendar.MINUTE, 59);
//        calendar.set(Calendar.SECOND, 59);
//        calendar.set(Calendar.MILLISECOND, 999);
//        return calendar.getTime();
//    }
}
