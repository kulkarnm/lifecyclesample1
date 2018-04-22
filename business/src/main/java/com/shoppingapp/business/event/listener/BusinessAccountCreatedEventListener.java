package com.shoppingapp.business.event.listener;

import com.shoppingapp.business.event.BusinessAccountCreatedEvent;
import com.shoppingapp.business.query.repository.BusinessAccountViewRepository;
import com.shoppingapp.business.query.view.BusinessAccountView;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BusinessAccountCreatedEventListener {
    private final BusinessAccountViewRepository businessAccountViewRepository;

    @Autowired
    public BusinessAccountCreatedEventListener(BusinessAccountViewRepository businessAccountViewRepository) {
        this.businessAccountViewRepository = businessAccountViewRepository;
    }

    @EventHandler
    public void on(BusinessAccountCreatedEvent event){
        List<BusinessAccountView> earlierBusinessAccounts =businessAccountViewRepository.findByEndDateGreaterThanEqual(event.getStartDate());
        if(null != earlierBusinessAccounts && !earlierBusinessAccounts.isEmpty()){
            for(BusinessAccountView businessAccount:earlierBusinessAccounts){
                businessAccount.setEndDate(event.getStartDate().minusDays(1));
                businessAccountViewRepository.save(businessAccount);
            }
        }
        BusinessAccountView businessAccountView = new BusinessAccountView(event.getId(),event.getMerchantId(),event.getStartDate(),event.getEndDate(),event.getDateOfProvision());
        businessAccountViewRepository.save(businessAccountView);
    }
}
