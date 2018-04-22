package com.verifier.domains.business.view;

import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "BusinessAccountView")
public class BusinessAccountView {

    @Id
    private String id;
    private String merchantId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate dateForProvision;


    public BusinessAccountView(String id, String merchantId,LocalDate startDate,LocalDate endDate,LocalDate dateForProvision) {
        this.id = id;
        this.merchantId=merchantId;
        this.startDate=startDate;
        this.endDate = endDate;
        this.dateForProvision = dateForProvision;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDateForProvision() {
        return dateForProvision;
    }

    public void setDateForProvision(LocalDate dateForProvision) {
        this.dateForProvision = dateForProvision;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "BusinessAccountView{" +
                "id='" + id + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", dateForProvision=" + dateForProvision +
                '}';
    }
}
