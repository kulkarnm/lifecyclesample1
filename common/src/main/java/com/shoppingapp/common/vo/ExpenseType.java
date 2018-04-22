package com.shoppingapp.common.vo;


public enum ExpenseType {
    COMMON_EXPENSE(0), SUBSCRIPTION_SPECIFIC_EXPENSE(1);

    private int expenseTypeCode;

    ExpenseType(int expenseTypeCode) {
        this.expenseTypeCode = expenseTypeCode;
    }

    public static ExpenseType valueOf(int expenseTypeCode) {
        switch (expenseTypeCode) {
            case 0:
                return COMMON_EXPENSE;
            case 1:
                return SUBSCRIPTION_SPECIFIC_EXPENSE;
            default:
                return COMMON_EXPENSE;
        }
    }

    public int getExpenseTypeCode() {
        return expenseTypeCode;
    }
}
