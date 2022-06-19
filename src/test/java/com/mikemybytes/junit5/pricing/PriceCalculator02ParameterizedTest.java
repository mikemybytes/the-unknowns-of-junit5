package com.mikemybytes.junit5.pricing;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PriceCalculator02ParameterizedTest {

    private final PriceCalculator calculator = new PriceCalculator();

    // + less structural duplication
    // - descriptive test case names got lost
    // - boring!

    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(Amount.of("10.99"), Amount.of("0.99"), Amount.of("10.00")),
                Arguments.of(Amount.of("10.99"), Amount.of("10.99"), Price.minimal().amount()),
                Arguments.of(Amount.of("10.99"), Amount.of("56.99"), Price.minimal().amount())
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void properlyCalculatesEffectiveAmount(Amount price, Amount discount, Amount expected) {
        // given
        var regularPrice = new Price("Regular", price);
        var campaign = new Campaign("Test Campaign", discount);
        // when
        Price effectivePrice = calculator.calculate(regularPrice, campaign);
        // then
        assertThat(effectivePrice.amount()).isEqualTo(expected);
    }

}