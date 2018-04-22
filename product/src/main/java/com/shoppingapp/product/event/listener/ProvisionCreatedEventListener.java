package com.shoppingapp.product.event.listener;

import com.shoppingapp.product.event.ProvisionCreatedEvent;
import com.shoppingapp.product.query.repository.BusinessAccountViewRepository;
import com.shoppingapp.product.query.view.BusinessAccountView;
import org.axonframework.eventhandling.EventHandler;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProvisionCreatedEventListener {

    private final BusinessAccountViewRepository businessAccountViewRepository;

    @Autowired
    public ProvisionCreatedEventListener(BusinessAccountViewRepository businessAccountViewRepository) {
        this.businessAccountViewRepository = businessAccountViewRepository;
    }

    @EventHandler
    public void on(ProvisionCreatedEvent event) {

        final Integer currentYear = event.getProvisionDate().getYear();
        LocalDateTime startDate = event.getProvisionDate();
        LocalDateTime endDate = new LocalDateTime(currentYear,12,31,12,59,59);

        businessAccountViewRepository.save(new BusinessAccountView(currentYear, startDate, endDate));
    }
}
