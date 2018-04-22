package com.shoppingapp.business.query.repository;

import com.shoppingapp.business.query.view.BusinessAccountView;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BusinessAccountViewRepository extends CrudRepository<BusinessAccountView, String> {
    List<BusinessAccountView> findByEndDateGreaterThanEqual(LocalDate date);
}
