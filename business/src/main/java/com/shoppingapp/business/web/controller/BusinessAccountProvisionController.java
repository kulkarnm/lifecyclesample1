package com.shoppingapp.business.web.controller;

import com.shoppingapp.business.command.CreateProvisionForBenefitsCommand;
import com.shoppingapp.business.command.CreateProvisionForPurchaseCostCommand;
import com.shoppingapp.business.command.CreateProvisionForTaxesCommand;
import com.shoppingapp.business.query.repository.BusinessAccountViewRepository;
import com.shoppingapp.business.web.request.BenefitsProvisionRequest;
import com.shoppingapp.business.web.request.PurchaseCostProvisionRequest;
import com.shoppingapp.business.web.request.TaxesProvisionRequest;
import com.shoppingapp.common.generator.DefaultIdGenerator;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "business/provision")
public class BusinessAccountProvisionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessAccountProvisionController.class);
    private final CommandGateway commandGateway;
    private final BusinessAccountViewRepository businessAccountViewRepository;
    private final DefaultIdGenerator defaultIdGenerator;
    @Autowired
    public BusinessAccountProvisionController(CommandGateway commandGateway,
                                              BusinessAccountViewRepository businessAccountViewRepository,
                                              DefaultIdGenerator defaultIdGenerator) {
        this.commandGateway = commandGateway;
        this.businessAccountViewRepository = businessAccountViewRepository;
        this.defaultIdGenerator=defaultIdGenerator;
    }

    @RequestMapping(method = RequestMethod.POST, value = "purchase")
    public ResponseEntity<Object> setProvisionForPurchaseCost(@RequestBody PurchaseCostProvisionRequest request) {
        String id= businessAccountViewRepository.findByEndDateGreaterThanEqual(LocalDate.now()).get(0).getId();
        CreateProvisionForPurchaseCostCommand command = new CreateProvisionForPurchaseCostCommand(id,request.getStartDate(), request.getEndDate(), request.getProvisionForPurchaseOfGoods());
        commandGateway.send(command);
        final Map<String, String> map = new HashMap<>(1);
        map.put("id", id);
        return new ResponseEntity<Object>(map,HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.POST, value = "benefits")
    public ResponseEntity<Object> setProvisionForBenefits (@RequestBody @Valid BenefitsProvisionRequest request) {
        String id= businessAccountViewRepository.findByEndDateGreaterThanEqual(LocalDate.now()).get(0).getId();
        CreateProvisionForBenefitsCommand command = new CreateProvisionForBenefitsCommand(id,request.getStartDate(), request.getEndDate(), request.getProvisionForBenefits());
        commandGateway.send(command);
        final Map<String, String> map = new HashMap<>(1);
        map.put("id", id);
        return new ResponseEntity<Object>(map,HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.POST, value = "taxes")

    public ResponseEntity<Object> setProvisionForTaxes(@RequestBody @Valid TaxesProvisionRequest request) {
        String id= businessAccountViewRepository.findByEndDateGreaterThanEqual(LocalDate.now()).get(0).getId();
        CreateProvisionForTaxesCommand command = new CreateProvisionForTaxesCommand(id,request.getStartDate(), request.getEndDate(), request.getProvisionForTaxes());
        commandGateway.send(command);
        final Map<String, String> map = new HashMap<>(1);
        map.put("id", id);
        return new ResponseEntity<Object>(map,HttpStatus.CREATED);
    }

}
