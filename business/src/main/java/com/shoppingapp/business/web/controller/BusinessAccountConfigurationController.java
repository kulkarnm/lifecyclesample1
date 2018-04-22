package com.shoppingapp.business.web.controller;

import com.shoppingapp.business.command.ConfigureBusinessAccountCommand;
import com.shoppingapp.business.command.CreateBusinessAccountCommand;
import com.shoppingapp.business.web.request.ConfigureBusinessAccountRequest;
import com.shoppingapp.business.web.request.CreateBusinessAccountRequest;
import com.shoppingapp.common.generator.DefaultIdGenerator;
import com.shoppingapp.common.vo.FiscalYearConfig;
import org.axonframework.commandhandling.gateway.CommandGateway;
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


@RestController
@RequestMapping(value = "business")
public class BusinessAccountConfigurationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessAccountConfigurationController.class);
    private final CommandGateway commandGateway;
    private final DefaultIdGenerator defaultIdGenerator;
    @Autowired
    public BusinessAccountConfigurationController(CommandGateway commandGateway, DefaultIdGenerator defaultIdGenerator) {
        this.commandGateway = commandGateway;
        this.defaultIdGenerator=defaultIdGenerator;
    }

    //Manual Creation of Business Account-For the firs time when setting up shoppingapp business
    @RequestMapping(method = RequestMethod.POST, value = "account")
    public ResponseEntity<Object> createBusinessAccount(@RequestBody @Valid CreateBusinessAccountRequest request) {
        String id= defaultIdGenerator.generator(request.getMerchantId()+request.getStartDate()+request.getEndDate());
        CreateBusinessAccountCommand command = new CreateBusinessAccountCommand(id, request.getMerchantId(),request.getStartDate(),request.getEndDate(),request.getDateOfProvision());
        commandGateway.send(command);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST,value="configure")
    public ResponseEntity<Object> configureBusinessAccount(@RequestBody @Valid ConfigureBusinessAccountRequest request) {
        String id = defaultIdGenerator.generator(request.getMerchantId()+request.getStartDateOfFinancialYear()+request.getEndDateOfFinancialYear());
        ConfigureBusinessAccountCommand command = new ConfigureBusinessAccountCommand(id, request.getBudgetSettingOptions(),new FiscalYearConfig(request.getStartDateOfFinancialYear(),request.getEndDateOfFinancialYear()));
        commandGateway.send(command);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }


}
