package com.shoppingapp.business.domain;

import com.shoppingapp.business.event.ProvisionForBenefitsRegisteredEvent;
import com.shoppingapp.business.vo.ProvisionSegment;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

public class BenefitsAccount extends ProvisionAccount {
    private static final Logger LOGGER = LoggerFactory.getLogger(BenefitsAccount.class);

    public BenefitsAccount(LocalDate startDate,LocalDate endDate) {
        super(startDate,endDate);
    }

    public void registerProvisionForBenefits(String id, LocalDate startDate, LocalDate endDate, double provisionForBenefits) {
        List<ProvisionSegment> distributionCalendar =distributeProvision(id,startDate,endDate,provisionForBenefits);
        apply(new ProvisionForBenefitsRegisteredEvent(id, startDate, endDate, provisionForBenefits,distributionCalendar));
    }

    @EventSourcingHandler
    public void on(ProvisionForBenefitsRegisteredEvent event) {
        registerProvision(event.getId(),event.getStartDate(),event.getEndDate(),event.getProvisionForBenefits(),event.getDistributionCalendar());
    }
}
