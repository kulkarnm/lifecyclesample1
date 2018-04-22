package com.shoppingapp.business.query.view;

import com.shoppingapp.business.vo.TransactionReasonCode;
import com.shoppingapp.business.vo.TransactionType;
import org.joda.time.LocalDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PurchaseCostAccountTransactionsView extends AccountTransactionsView {
    public PurchaseCostAccountTransactionsView(LocalDate dateOfTransaction, double transactedAmount, TransactionType transactionType, TransactionReasonCode transactionReasonCode) {
        super(dateOfTransaction,transactedAmount,transactionType, transactionReasonCode);
    }

}
