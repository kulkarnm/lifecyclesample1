package com.shoppingapp.business.domain;

import com.shoppingapp.business.event.ProvisionForPurchaseCostRegisteredEvent;
import com.shoppingapp.business.vo.ProvisionSegment;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

public class PurchaseCostAccount extends ProvisionAccount{
    private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseCostAccount.class);
    public PurchaseCostAccount(LocalDate startDate, LocalDate endDate) {
        super(startDate,endDate);
    }


    public void registerProvisionForPurchaseCost(String id, LocalDate startDate, LocalDate endDate, double provisionForPurchaseOfGoods) {
        List<ProvisionSegment> distributionCalendar = distributeProvision(id,startDate,endDate,provisionForPurchaseOfGoods);
        apply(new ProvisionForPurchaseCostRegisteredEvent(id, startDate, endDate, provisionForPurchaseOfGoods,distributionCalendar));
    }

    @EventSourcingHandler
    public void on(ProvisionForPurchaseCostRegisteredEvent event) {
        registerProvision(event.getId(),event.getStartDate(),event.getEndDate(),event.getProvisionForPurchaseOfGoods(),event.getProvisionDistribution());
    }
}
