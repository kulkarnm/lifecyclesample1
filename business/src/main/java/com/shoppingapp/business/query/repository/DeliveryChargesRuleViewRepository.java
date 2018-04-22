package com.shoppingapp.business.query.repository;

import com.shoppingapp.business.query.view.DeliveryChargesRuleView;
import com.shoppingapp.common.vo.QuantityUnit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeliveryChargesRuleViewRepository extends CrudRepository<DeliveryChargesRuleView, String> {
    List<DeliveryChargesRuleView> findByRuleMinimumAndRuleMaximumAndRuleUnit(double ruleMinimum, double ruleMaximum, QuantityUnit ruleUnit);
}
