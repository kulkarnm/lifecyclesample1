package com.shoppingapp.business.event.listener;

import com.shoppingapp.business.event.BusinessAccountConfigurationSetEvent;
import com.shoppingapp.business.query.repository.BusinessAccountConfigurationViewRepository;
import com.shoppingapp.business.query.view.BusinessAccountConfigurationView;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusinessAccountConfigurationSetEventListener {
    private final BusinessAccountConfigurationViewRepository businessAccountConfigurationViewRepository;
    @Autowired
    public BusinessAccountConfigurationSetEventListener(BusinessAccountConfigurationViewRepository businessAccountConfigurationViewRepository) {
        this.businessAccountConfigurationViewRepository = businessAccountConfigurationViewRepository;
    }

    @EventHandler
    public void on(BusinessAccountConfigurationSetEvent event){
        String businessAccountId= event.getId();
        BusinessAccountConfigurationView businessAccountConfigurationView= businessAccountConfigurationViewRepository.findOne(businessAccountId);
        if(null== businessAccountConfigurationView){
            businessAccountConfigurationView= new BusinessAccountConfigurationView(event.getId(),event.getBudgetSettingOptions(),event.getFiscalYearConfig());
        }else{
            if(null != event.getBudgetSettingOptions()) {
                businessAccountConfigurationView.setBudgetSettingOptions(event.getBudgetSettingOptions());
            }
            if(null != event.getFiscalYearConfig()){
                businessAccountConfigurationView.setFiscalYearConfig(event.getFiscalYearConfig());
            }

        }
        businessAccountConfigurationViewRepository.save(businessAccountConfigurationView);
    }
}
