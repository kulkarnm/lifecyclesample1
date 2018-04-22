package com.shoppingapp.business.event.listener;

import com.shoppingapp.business.event.ProvisionForTaxesRegisteredEvent;
import com.shoppingapp.business.query.repository.TaxesAccountTransactionsViewRepository;
import com.shoppingapp.business.query.repository.TaxesAccountViewRepository;
import com.shoppingapp.business.query.view.TaxesAccountTransactionsView;
import com.shoppingapp.business.query.view.TaxesAccountView;
import com.shoppingapp.business.vo.TransactionReasonCode;
import com.shoppingapp.business.vo.TransactionType;
import org.axonframework.eventhandling.EventHandler;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProvisionForTaxesRegisteredEventListener {
    private TaxesAccountViewRepository taxesAccountViewRepository;
    private TaxesAccountTransactionsViewRepository taxesAccountTransactionsViewRepository;
    @Autowired
    public ProvisionForTaxesRegisteredEventListener(TaxesAccountViewRepository taxesAccountViewRepository, TaxesAccountTransactionsViewRepository taxesAccountTransactionsViewRepository) {
        this.taxesAccountViewRepository = taxesAccountViewRepository;
        this.taxesAccountTransactionsViewRepository = taxesAccountTransactionsViewRepository;
    }

    @EventHandler
    public void on (ProvisionForTaxesRegisteredEvent event){
        TaxesAccountView taxesAccountView= new TaxesAccountView(event.getId(),event.getProvisionForTaxes(),event.getProvisionForTaxes(),event.getStartDate().toLocalDateTime(LocalTime.now()),event.getEndDate().toLocalDateTime(LocalTime.MIDNIGHT));
        taxesAccountViewRepository.save(taxesAccountView);
        TaxesAccountTransactionsView taxesAccountTransactionsView= new TaxesAccountTransactionsView(event.getStartDate(),event.getProvisionForTaxes(), TransactionType.CREDIT, TransactionReasonCode.TAXES_PROVISION_REGISTERED);
        taxesAccountTransactionsViewRepository.save(taxesAccountTransactionsView);
    }
}
