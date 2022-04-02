package com.mikemybytes.junit5.duration;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class DurationEncoder03Test {

    private final DurationEncoder encoder = new DurationEncoder();

    @ParameterizedTest(name = "{0} seconds encoded as {1}")
    @CsvSource(delimiterString = "seconds encoded as", textBlock = """
                15 seconds encoded as 'PT15S'
                180 seconds encoded as 'PT3M'
                8_047 seconds encoded as 'PT2H14M7S'
                172_800 seconds encoded as 'PT48H'
            """
    )
    void encodesAsIso8601(long seconds, String expected) {
        String encoded = encoder.secondsToIso8601(seconds);
        assertThat(encoded).isEqualTo(expected);
    }

    // alternatives: "maps to", "represented as", "becomes", "is", ...

}