package com.shoppingapp.business.vo;

import com.shoppingapp.common.vo.ExpenseType;
import com.shoppingapp.common.vo.Period;
import com.shoppingapp.common.vo.SensitivityCharacteristic;

public class OperatingExpenseVO {
    private CommonOperatingExpenseHeader expenseHeader;
    private double amount;
    private Period period;
    private SensitivityCharacteristic sensitivityCharacteristic;
    private ExpenseType expenseType;


    public CommonOperatingExpenseHeader getExpenseHeader() {
        return expenseHeader;
    }

    public void setExpenseHeader(CommonOperatingExpenseHeader expenseHeader) {
        this.expenseHeader = expenseHeader;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }

    public SensitivityCharacteristic getSensitivityCharacteristic() {
        return this.sensitivityCharacteristic;
    }

    public void setSensitivityCharacteristic(SensitivityCharacteristic sensitivityCharacteristic) {
        this.sensitivityCharacteristic = sensitivityCharacteristic;
    }
}
