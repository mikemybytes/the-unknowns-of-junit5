package com.mikemybytes.junit5.duration;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class DurationEncoder02Test {

    private final DurationEncoder encoder = new DurationEncoder();

    // TODO: what about test case names?

    @ParameterizedTest
    @CsvSource(delimiterString = "->", useHeadersInDisplayName = true, textBlock = """
                seconds  -> ISO8601
                15       -> PT15S
                180      -> PT3M
                8047     -> PT2H14M7S
                172800   -> PT48H
            """
    )
    void encodesAsIso8601(long seconds, String expected) {
        String encoded = encoder.secondsToIso8601(seconds);
        assertThat(encoded).isEqualTo(expected);
    }

    // alternatives: "maps to", "represented as", "becomes", "is", ...

}