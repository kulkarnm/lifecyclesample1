package com.shoppingapp.business.event.listener;

import com.shoppingapp.business.event.ProvisionForPurchaseCostRegisteredEvent;
import com.shoppingapp.business.query.repository.PurchaseCostAccountTransactionsViewRepository;
import com.shoppingapp.business.query.repository.PurchaseCostAccountViewRepository;
import com.shoppingapp.business.query.view.PurchaseCostAccountTransactionsView;
import com.shoppingapp.business.query.view.PurchaseCostAccountView;
import com.shoppingapp.business.vo.TransactionReasonCode;
import com.shoppingapp.business.vo.TransactionType;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProvisionForPurchaseCostRegisteredEventListener {
    private PurchaseCostAccountViewRepository purchaseCostAccountViewRepository;
    private PurchaseCostAccountTransactionsViewRepository purchaseCostAccountTransactionsViewRepository;

    @Autowired
    public ProvisionForPurchaseCostRegisteredEventListener(PurchaseCostAccountViewRepository purchaseCostAccountViewRepository, PurchaseCostAccountTransactionsViewRepository purchaseCostAccountTransactionsViewRepository) {
        this.purchaseCostAccountViewRepository = purchaseCostAccountViewRepository;
        this.purchaseCostAccountTransactionsViewRepository = purchaseCostAccountTransactionsViewRepository;
    }

    @EventHandler
    public void on(ProvisionForPurchaseCostRegisteredEvent event){
        PurchaseCostAccountView purchaseCostAccountView= new PurchaseCostAccountView(event.getId(),event.getProvisionForPurchaseOfGoods(),event.getProvisionDistribution(),event.getStartDate(),event.getEndDate());
        purchaseCostAccountViewRepository.save(purchaseCostAccountView);
        PurchaseCostAccountTransactionsView purchaseCostAccountTransactionsView= new PurchaseCostAccountTransactionsView(event.getStartDate(),event.getProvisionForPurchaseOfGoods(), TransactionType.CREDIT, TransactionReasonCode.PURCHASE_COST_PROVISION_REGISTERED);
        purchaseCostAccountTransactionsViewRepository.save(purchaseCostAccountTransactionsView);
    }
}
