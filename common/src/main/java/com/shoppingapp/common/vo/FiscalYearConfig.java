package com.shoppingapp.common.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;


public class FiscalYearConfig {
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using=LocalDateDeserializer.class)
    private LocalDate startDateOfFinanicalYear;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using=LocalDateDeserializer.class)
    private LocalDate endDateOfFinancialYear;
    private int jodaStartMonthOfFinancialYear;
    private int jodEndMonthOfFinancialYear;

    public FiscalYearConfig(){

    }
    public FiscalYearConfig(LocalDate startDateOfFinanicalYear, LocalDate endDateOfFinancialYear) {
        this.startDateOfFinanicalYear=startDateOfFinanicalYear;
        this.endDateOfFinancialYear=endDateOfFinancialYear;
        this.jodaStartMonthOfFinancialYear = startDateOfFinanicalYear.getMonthOfYear();
        this.jodEndMonthOfFinancialYear = endDateOfFinancialYear.getMonthOfYear();
    }

    public LocalDate getStartDateOfFinanicalYear() {
        return startDateOfFinanicalYear;
    }

    public LocalDate getEndDateOfFinancialYear() {
        return endDateOfFinancialYear;
    }

    public int getJodaStartMonthOfFinancialYear() {
        return jodaStartMonthOfFinancialYear;
    }

    public int getJodEndMonthOfFinancialYear() {
        return jodEndMonthOfFinancialYear;
    }
}
