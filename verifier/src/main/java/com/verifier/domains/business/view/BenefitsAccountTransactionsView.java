package com.verifier.domains.business.view;

import com.verifier.domains.business.vo.TransactionReasonCode;
import com.verifier.domains.business.vo.TransactionType;
import org.joda.time.LocalDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class BenefitsAccountTransactionsView extends AccountTransactionsView {
    public BenefitsAccountTransactionsView(LocalDate dateOfTransaction, double transactedAmount, TransactionType transactionType, TransactionReasonCode transactionReasonCode) {
        super(dateOfTransaction,transactedAmount,transactionType, transactionReasonCode);
    }

}
