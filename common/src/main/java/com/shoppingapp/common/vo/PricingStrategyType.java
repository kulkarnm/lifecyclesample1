package com.shoppingapp.common.vo;

public enum PricingStrategyType {

    DEFAULT_PRICING_STRATEGY(0),
    DEMAND_BASED_PRICING_STRATEGY(1),
    DEMAND_AND_COST_BASED_PRICING_STRATEGY(2);

    private int strategyType;

    PricingStrategyType(int strategyType) {
        this.strategyType = strategyType;
    }

    public static PricingStrategyType valueOf(int value) {
        for (PricingStrategyType r : PricingStrategyType.values()) {
            if (r.getStrategyType() == value) {
                return r;
            }
        }
        throw new IllegalArgumentException();
    }

    public int getStrategyType() {
        return strategyType;
    }

}
