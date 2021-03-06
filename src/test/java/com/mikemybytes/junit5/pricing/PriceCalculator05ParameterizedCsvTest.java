package com.mikemybytes.junit5.pricing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PriceCalculator05ParameterizedCsvTest {

    private final PriceCalculator calculator = new PriceCalculator();

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

    // TODO: what about parameter names?

    @ParameterizedTest
    @CsvSource(delimiter = '|', textBlock = """
             5.99 |  5.99
            10.99 | 56.99
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