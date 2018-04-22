package com.verifier.domains.business.repository;

import com.verifier.domains.business.view.BusinessAccountView;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BusinessBusinessAccountViewRepository extends CrudRepository<BusinessAccountView, String> {
    List<BusinessAccountView> findByEndDateAfter(LocalDate date);
}
