package com.verifier.domains.business.repository;

import com.verifier.domains.business.view.BusinessAccountConfigurationView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BusinessBusinessAccountConfigurationViewRepository extends CrudRepository<BusinessAccountConfigurationView,String> {
    List<BusinessAccountConfigurationView> findAll();
}
