package com.java.alami.spesifications;

import com.java.alami.entity.Transaction;
import com.java.alami.filters.BaseFilter;
import com.java.alami.filters.TransactionFilter;

public class TransactionSpecification extends BaseSpecification<Transaction> {

    public TransactionSpecification(BaseFilter baseFilter) {
        super(baseFilter);
    }

    @Override
    protected void setCriteriaList(BaseFilter baseFilter) {
        TransactionFilter casted = (TransactionFilter) baseFilter;
        if(casted.getStartDate()!=null){
            this.list.add(new SearchCriteria("created_at", casted.getStartDate(), SearchOperation.GREATER_THAN_EQUAL_DATE));
        }
        if(casted.getEndDate()!=null){
            this.list.add(new SearchCriteria("created_at", casted.getEndDate(), SearchOperation.LESS_THAN_EQUAL_DATE));
        }
    }

}