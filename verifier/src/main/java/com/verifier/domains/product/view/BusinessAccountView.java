package com.verifier.domains.product.view;

import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "BusinessAccountView")
public class BusinessAccountView {

    @Id
    private Integer id;
    private LocalDateTime dateForProvision;
    private LocalDateTime endDate;

    public BusinessAccountView(Integer id, LocalDateTime dateForProvision, LocalDateTime endDate) {
        this.id = id;
        this.dateForProvision = dateForProvision;
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateForProvision() {
        return dateForProvision;
    }

    public void setDateForProvision(LocalDateTime dateForProvision) {
        this.dateForProvision = dateForProvision;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

}
