package com.mikemybytes.junit5.pricing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PriceCalculator06ParameterizedCsvTest {

    private final PriceCalculator calculator = new PriceCalculator();

    @Test
    @DisplayName("applies discount to the regular price")
    void appliesDiscountToRegularPrice() {
        // given
        var regularPrice = new Price("Regular", Amount.of("10.99"));
        var campaign = new Campaign("Test Campaign", Amount.of("0.99"));
        // when
        Price effectivePrice = calculator.calculate(regularPrice, campaign);
        // then
        assertThat(effectivePrice.amount()).isEqualTo(Amount.of("10.00"));
    }

    // TODO: how to make the 'description' a part of the test case name?

    @ParameterizedTest
    @CsvSource(delimiter = '|', textBlock = """
            # regular | discount | description
                 5.99 |     5.99 | discount same as the regular price
                10.99 |    56.99 | discount greater than the regular price
            """)
    void fallsBackToMinimalPriceWhenNecessary(Amount regular, Amount discount) {
        // given
        var regularPrice = new Price("Regular", regular);
        var campaign = new Campaign("Test Campaign", discount);
        // when
        Price effectivePrice = calculator.calculate(regularPrice, campaign);
        // then
        assertThat(effectivePrice.amount()).isEqualTo(Price.minimal().amount());
    }

}