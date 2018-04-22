package com.verifier.controller;


import com.verifier.domains.business.repository.*;
import com.verifier.domains.business.view.*;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "business")
public class BusinessVerifierController {

    private final BusinessBusinessAccountViewRepository businessBusinessAccountViewRepository;
    private final BusinessBusinessAccountConfigurationViewRepository businessBusinessAccountConfigurationViewRepository;
    private final BusinessPurchaseCostAccountViewRepository businessPurchaseCostAccountViewRepository;
    private final BusinessPurchaseCostAccountTransactionsViewRepository businessPurchaseCostAccountTransactionsViewRepository;
    private final BusinessBenefitAccountViewRepository businessBenefitAccountViewRepository;
    private final BusinessBenefitAccountTransactionsViewRepository businessBenefitAccountTransactionsViewRepository;
    private final BusinessProductViewRepository businessProductViewRepository;
    private final BusinessTaxesAccountViewRepository businessTaxesAccountViewRepository;
    private final BusinessTaxesAccountTransactionsViewRepository businessTaxesAccountTransactionsViewRepository;

    @Autowired
    public BusinessVerifierController(BusinessBusinessAccountViewRepository businessBusinessAccountViewRepository, BusinessBusinessAccountConfigurationViewRepository businessBusinessAccountConfigurationViewRepository, BusinessPurchaseCostAccountViewRepository businessPurchaseCostAccountViewRepository, BusinessPurchaseCostAccountTransactionsViewRepository businessPurchaseCostAccountTransactionsViewRepository,BusinessBenefitAccountViewRepository businessBenefitAccountViewRepository, BusinessBenefitAccountTransactionsViewRepository businessBenefitAccountTransactionsViewRepository, BusinessProductViewRepository businessProductViewRepository, BusinessTaxesAccountViewRepository businessTaxesAccountViewRepository, BusinessTaxesAccountTransactionsViewRepository businessTaxesAccountTransactionsViewRepository) {
        this.businessBusinessAccountViewRepository = businessBusinessAccountViewRepository;
        this.businessBusinessAccountConfigurationViewRepository = businessBusinessAccountConfigurationViewRepository;
        this.businessPurchaseCostAccountViewRepository = businessPurchaseCostAccountViewRepository;
        this.businessPurchaseCostAccountTransactionsViewRepository = businessPurchaseCostAccountTransactionsViewRepository;
        this.businessBenefitAccountViewRepository = businessBenefitAccountViewRepository;
        this.businessBenefitAccountTransactionsViewRepository = businessBenefitAccountTransactionsViewRepository;
        this.businessProductViewRepository = businessProductViewRepository;
        this.businessTaxesAccountViewRepository = businessTaxesAccountViewRepository;
        this.businessTaxesAccountTransactionsViewRepository = businessTaxesAccountTransactionsViewRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "businessaccount")
    ResponseEntity<BusinessAccountView> getBusinessAccount(){
        BusinessAccountView businessAccountView=null;
        List<BusinessAccountView> views = businessBusinessAccountViewRepository.findByEndDateAfter(LocalDate.now());
        if(null != views && !views.isEmpty()) {
            businessAccountView =views.get(0);
        }
        return new ResponseEntity<BusinessAccountView>(businessAccountView,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "businessaccount/configure")
    ResponseEntity<BusinessAccountConfigurationView> getBusinessAccountConfiguration(){
        List<BusinessAccountConfigurationView> configurations = businessBusinessAccountConfigurationViewRepository.findAll();
        return new ResponseEntity<BusinessAccountConfigurationView>(configurations.get(0), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "purchaseaccount/{id}")
    ResponseEntity<PurchaseCostAccountView> getPurchaseAccount(@PathVariable String id){
        PurchaseCostAccountView purchaseCostAccountView= businessPurchaseCostAccountViewRepository.findOne(id);
        return new ResponseEntity<PurchaseCostAccountView>(purchaseCostAccountView,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "purchaseaccount/transaction/{dateOfTransaction}")
    ResponseEntity<List<PurchaseCostAccountTransactionsView>> getPurchaseAccountTransaction(@PathVariable LocalDate dateOfTransaction){
        List<PurchaseCostAccountTransactionsView> purchaseCostAccountTransactionsViews = businessPurchaseCostAccountTransactionsViewRepository.findByDateOfTransaction(dateOfTransaction);
        return new ResponseEntity<List<PurchaseCostAccountTransactionsView>>(purchaseCostAccountTransactionsViews,HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "benefitaccount/{id}")
    ResponseEntity<BenefitAccountView> getBenefitAccount(@PathVariable String id){
        return new ResponseEntity<BenefitAccountView>(businessBenefitAccountViewRepository.findOne(id),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "benefitaccount/transaction/{dateOfTransaction}")
    ResponseEntity<List<BenefitsAccountTransactionsView>> getBenefitAccountTransaction(@PathVariable LocalDate dateOfTransaction){
        return new ResponseEntity<List<BenefitsAccountTransactionsView>>(businessBenefitAccountTransactionsViewRepository.findByDateOfTransaction(dateOfTransaction),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "product/{id}")
    ResponseEntity<ProductView> getProductView(@PathVariable String id){
        return new ResponseEntity<ProductView>(businessProductViewRepository.findByProductId(id),HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "taxesaccount/{id}")
    ResponseEntity<TaxesAccountView> getTaxesAccount(@PathVariable String id){
        return new ResponseEntity<TaxesAccountView>(businessTaxesAccountViewRepository.findOne(id),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "taxesaccount/transaction/{dateOfTransaction}")
    ResponseEntity<List<TaxesAccountTransactionsView>> getTaxesAccountTransaction(@PathVariable LocalDate dateOfTransaction){
        return new ResponseEntity<List<TaxesAccountTransactionsView>>(businessTaxesAccountTransactionsViewRepository.findByDateOfTransaction(dateOfTransaction),HttpStatus.OK);
    }

}
