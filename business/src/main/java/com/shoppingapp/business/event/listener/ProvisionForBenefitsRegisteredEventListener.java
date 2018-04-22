package com.shoppingapp.business.event.listener;

import com.shoppingapp.business.event.ProvisionForBenefitsRegisteredEvent;
import com.shoppingapp.business.query.repository.BenefitAccountTransactionsViewRepository;
import com.shoppingapp.business.query.repository.BenefitAccountViewRepository;
import com.shoppingapp.business.query.view.BenefitAccountView;
import com.shoppingapp.business.query.view.BenefitsAccountTransactionsView;
import com.shoppingapp.business.vo.TransactionReasonCode;
import com.shoppingapp.business.vo.TransactionType;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProvisionForBenefitsRegisteredEventListener {
    private BenefitAccountViewRepository benefitAccountViewRepository;
    private BenefitAccountTransactionsViewRepository benefitAccountTransactionsViewRepository;

    @Autowired
    public ProvisionForBenefitsRegisteredEventListener(BenefitAccountViewRepository benefitAccountViewRepository, BenefitAccountTransactionsViewRepository benefitAccountTransactionsViewRepository) {
        this.benefitAccountViewRepository = benefitAccountViewRepository;
        this.benefitAccountTransactionsViewRepository = benefitAccountTransactionsViewRepository;
    }

    @EventHandler
    public void on(ProvisionForBenefitsRegisteredEvent event){
        BenefitAccountView benefitAccountView= new BenefitAccountView(event.getId(),event.getProvisionForBenefits(),event.getDistributionCalendar(),event.getStartDate(),event.getEndDate());
        benefitAccountViewRepository.save(benefitAccountView);
        BenefitsAccountTransactionsView benefitAccountTransactionsView= new BenefitsAccountTransactionsView(event.getStartDate(),event.getProvisionForBenefits(), TransactionType.CREDIT, TransactionReasonCode.BENEFITS_PROVISION_REGISTERED);
        benefitAccountTransactionsViewRepository.save(benefitAccountTransactionsView);
    }

}
