package com.mikemybytes.junit5.pricing;

class PriceCalculator {

    // could be part of the Price to avoid anemic domain model

    Price calculate(Price price, Campaign campaign) {
        if (price.amount().isLessThan(campaign.discount())) {
            return Price.minimal();
        }
        var discountedAmount = price.amount().subtract(campaign.discount());
        var discountedPrice = new Price(price.name() + " (discounted)", discountedAmount);
        if (discountedPrice.amount().isLessThan(Price.minimal().amount())) {
            return Price.minimal();
        }
        return discountedPrice;
    }

}
