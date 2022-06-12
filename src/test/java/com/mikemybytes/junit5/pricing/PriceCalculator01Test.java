package com.mikemybytes.junit5.pricing;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PriceCalculator01Test {

    private final PriceCalculator calculator = new PriceCalculator();

    // structural duplication

    @Test
    void appliesDiscountToRegularPrice() {
        // given
        var regularPrice = new Price("Regular", Amount.of("10.99"));
        var campaign = new Campaign("Test Campaign", Amount.of("0.99"));
        // when
        Price effectivePrice = calculator.calculate(regularPrice, campaign);
        // then
        assertThat(effectivePrice.amount()).isEqualTo(Amount.of("10.00"));
    }

    @Test
    void fallsBackToMinimalPriceWhenFullDiscount() {
        // given
        var regularPrice = new Price("Regular", Amount.of("10.99"));
        var campaign = new Campaign("Test Campaign", Amount.of("10.99"));
        // when
        Price effectivePrice = calculator.calculate(regularPrice, campaign);
        // then
        assertThat(effectivePrice.amount()).isEqualTo(Price.minimal().amount());
    }

    @Test
    void fallsBackToMinimalWhenDiscountGreaterThanRegularPrice() {
        // given
        var regularPrice = new Price("Regular", Amount.of("10.99"));
        var campaign = new Campaign("Test Campaign", Amount.of("56.99"));
        // when
        Price effectivePrice = calculator.calculate(regularPrice, campaign);
        // then
        assertThat(effectivePrice.amount()).isEqualTo(Price.minimal().amount());
    }

}