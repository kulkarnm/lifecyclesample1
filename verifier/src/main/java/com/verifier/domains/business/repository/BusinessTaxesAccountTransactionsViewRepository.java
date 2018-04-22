package com.verifier.domains.business.repository;

import com.verifier.domains.business.view.TaxesAccountTransactionsView;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BusinessTaxesAccountTransactionsViewRepository extends CrudRepository<TaxesAccountTransactionsView, Long> {
    List<TaxesAccountTransactionsView> findByDateOfTransaction(LocalDate dateOfTransaction);
}
