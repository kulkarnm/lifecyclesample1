package com.verifier.domains.business.repository;

import com.verifier.domains.business.view.BenefitsAccountTransactionsView;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BusinessBenefitAccountTransactionsViewRepository extends CrudRepository<BenefitsAccountTransactionsView, Long> {
    List<BenefitsAccountTransactionsView> findByDateOfTransaction(LocalDate dateOfTransaction);
}
