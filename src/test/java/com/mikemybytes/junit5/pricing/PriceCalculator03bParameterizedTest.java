package com.mikemybytes.junit5.pricing;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Named.named;

class PriceCalculator03bParameterizedTest {

    private final PriceCalculator calculator = new PriceCalculator();

    // + nice test case names
    // - still pretty verbose

    record PricingTestCase(Amount price, Amount discount, Amount expected) {
    }

    static Stream<Named<PricingTestCase>> testCases() { // Named available from 5.8
        return Stream.of(
                Named.of(
                        "discount smaller than the regular price",
                        new PricingTestCase(Amount.of("10.99"), Amount.of("0.99"), Amount.of("10.00"))
                ),
                Named.named( // same as .of(...) but nicer for static importing
                        "discount same as the regular price",
                        new PricingTestCase(Amount.of("10.99"), Amount.of("10.99"), Price.minimal().amount())
                ),
                named(
                        "discount greater than the regular price",
                        new PricingTestCase(Amount.of("10.99"), Amount.of("56.99"), Price.minimal().amount())
                )
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