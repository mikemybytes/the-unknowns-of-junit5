package com.mikemybytes.junit5.pricing;

class PriceCalculator {

    // could be part of the Price to avoid anemic domain model

    Price calculate(Price price, Campaign campaign) {
        var discountedAmount = price.amount().subtract(campaign.discount());

        if (discountedAmount.isLessThan(Price.minimal().amount())) {
            return Price.minimal();
        }

        return new Price(price.name() + " (discounted)", discountedAmount);
    }

}
