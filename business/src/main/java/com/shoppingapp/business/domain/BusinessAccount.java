package com.shoppingapp.business.domain;

import com.shoppingapp.business.event.BusinessAccountConfigurationSetEvent;
import com.shoppingapp.business.event.BusinessAccountCreatedEvent;
import com.shoppingapp.business.vo.BudgetSettingOptions;
import com.shoppingapp.common.vo.FiscalYearConfig;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.commandhandling.model.AggregateRoot;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.joda.time.LocalDate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@AggregateRoot
public class BusinessAccount {
    @AggregateIdentifier
    private String id;
    private String merchantId;
    private LocalDate startDate;
    private LocalDate endDate;

    private long totalSubscriptionsRegistered;

    @AggregateMember
    private PurchaseCostAccount provisionalPurchaseCostAccount;

    @AggregateMember
    private BenefitsAccount provisionalBenefitsAccount;
    @AggregateMember
    private TaxesAccount provisionalTaxesAccount;

    private BusinessAccountConfiguration businessAccountConfiguration;

    private LocalDate dateForProvision;

    public BusinessAccount() {

    }

    public BusinessAccount(String id, String merchantId, LocalDate startDate, LocalDate endDate, LocalDate dateOfProvision) {
        apply(new BusinessAccountCreatedEvent(id, merchantId, startDate, endDate, dateOfProvision));
    }

    public String getId() {
        return id;
    }

    public PurchaseCostAccount getProvisionalPurchaseCostAccount() {
        return provisionalPurchaseCostAccount;
    }


    public BenefitsAccount getProvisionalBenefitsAccount() {
        return provisionalBenefitsAccount;
    }

    public TaxesAccount getProvisionalTaxesAccount() {
        return provisionalTaxesAccount;
    }

    public LocalDate getDateForProvision() {
        return dateForProvision;
    }

    public long getTotalSubscriptionsRegistered() {
        return totalSubscriptionsRegistered;
    }

    public void setTotalSubscriptionsRegistered(long totalSubscriptionsRegistered) {
        this.totalSubscriptionsRegistered = totalSubscriptionsRegistered;
    }


    @EventSourcingHandler
    public void on(BusinessAccountCreatedEvent event) {
        this.id = event.getId();
        this.merchantId = event.getMerchantId();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        this.dateForProvision = event.getDateOfProvision();
        LocalDate endDate = dateForProvision.year().withMaximumValue();
        this.provisionalPurchaseCostAccount = new PurchaseCostAccount(dateForProvision, endDate);
        this.provisionalBenefitsAccount = new BenefitsAccount(dateForProvision, endDate);
        this.provisionalTaxesAccount = new TaxesAccount(dateForProvision, endDate);
        this.businessAccountConfiguration = new BusinessAccountConfiguration(this.id);
    }


    public void registerProvisionForPurchaseCost(String id, LocalDate startDate, LocalDate endDate, double provisionForPurchaseOfGoods) {
        this.getProvisionalPurchaseCostAccount().registerProvisionForPurchaseCost(id, startDate, endDate, provisionForPurchaseOfGoods);
    }

    public void registerProvisionForBenefits(String id, LocalDate startDate, LocalDate endDate, double provisionForBenefits) {
        this.getProvisionalBenefitsAccount().registerProvisionForBenefits(id, startDate, endDate, provisionForBenefits);
    }

    public void registerProvisionForTaxes(String id, LocalDate startDate, LocalDate endDate, double provisionForTaxes) {
        this.getProvisionalTaxesAccount().registerProvisionForTaxes(id, startDate, endDate, provisionForTaxes);
    }

    public void configureBusinessAccount(BudgetSettingOptions budgetSettingOptions, FiscalYearConfig fiscalYearConfig) {
        apply(new BusinessAccountConfigurationSetEvent(this.id, budgetSettingOptions, fiscalYearConfig));
    }

    @EventSourcingHandler
    public void on(BusinessAccountConfigurationSetEvent event) {
        this.businessAccountConfiguration.setBudgetSettingOptions(event.getBudgetSettingOptions());
        this.businessAccountConfiguration.setFiscalYearConfig(event.getFiscalYearConfig());
    }
}
