package com.mikemybytes.junit5.pricing;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PriceCalculator03aParameterizedTest {

    private final PriceCalculator calculator = new PriceCalculator();

    // + type safety!
    // + (a bit) better test case names (toString())
    // - still not fancy enough

    record PricingTestCase(Amount price, Amount discount, Amount expected) {
    }

    static Stream<PricingTestCase> testCases() {
        return Stream.of(
                new PricingTestCase(Amount.of("10.99"), Amount.of("0.99"), Amount.of("10.00")),
                new PricingTestCase(Amount.of("10.99"), Amount.of("10.99"), Price.minimal().amount()),
                new PricingTestCase(Amount.of("10.99"), Amount.of("56.99"), Price.minimal().amount())
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void properlyCalculatesEffectiveAmount(PricingTestCase testCase) {
        // given
        var regularPrice = new Price("Regular", testCase.price());
        var campaign = new Campaign("Test Campaign", testCase.discount());
        // when
        Price effectivePrice = calculator.calculate(regularPrice, campaign);
        // then
        assertThat(effectivePrice.amount()).isEqualTo(testCase.expected());
    }

}