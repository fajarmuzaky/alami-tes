package com.java.alami.constants;

public class TransactionTypes {
    public static final int DEPOSIT = 1;
    public static final int lOAN = 2;
    public static final int WITHDRAW = 3;
    public static final int REPAYMENT = 4;

    public static int getTransactionTypeId(String type){
        String t = type.toUpperCase();
        int typeId = 0;
        switch (t) {
            case "DEPOSIT":
                typeId = TransactionTypes.DEPOSIT;
                break;
            case "LOAN":
                typeId = TransactionTypes.lOAN;
                break;
            case "WITHDRAW":
                typeId = TransactionTypes.WITHDRAW;
                break;
            case "REPAYMENT":
                typeId = TransactionTypes.REPAYMENT;
                break;
        }
        return typeId;
    }

    public static String getTransactionTypeName(int typeId){
        String typeName = null;
        switch (typeId) {
            case TransactionTypes.DEPOSIT:
                typeName = "DEPOSIT";
                break;
            case TransactionTypes.lOAN:
                typeName = "LOAN";
                break;
            case TransactionTypes.WITHDRAW:
                typeName = "WITHDRAW";
                break;
            case TransactionTypes.REPAYMENT:
                typeName = "REPAYMENT";
                break;
        }
        return typeName;
    }
}
