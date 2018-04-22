package com.shoppingapp.business.domain;

import com.shoppingapp.business.event.ProvisionForTaxesRegisteredEvent;
import com.shoppingapp.business.vo.ProvisionSegment;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

public class TaxesAccount extends ProvisionAccount {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaxesAccount.class);

    public TaxesAccount(LocalDate startDate, LocalDate endDate) {
        super(startDate, endDate);
    }

    public void registerProvisionForTaxes(String id, LocalDate startDate, LocalDate endDate, double provisionForTaxes) {
        List<ProvisionSegment> provisionDistribution = distributeProvision(id,startDate,endDate,provisionForTaxes);
        apply(new ProvisionForTaxesRegisteredEvent(id, startDate, endDate, provisionForTaxes,provisionDistribution));
    }

    @EventSourcingHandler
    public void on(ProvisionForTaxesRegisteredEvent event) {
        registerProvision(event.getId(),event.getStartDate(),event.getEndDate(),event.getProvisionForTaxes(),event.getProvisionDistribution());
    }

}
