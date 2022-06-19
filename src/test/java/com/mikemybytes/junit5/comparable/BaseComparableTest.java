package com.mikemybytes.junit5.comparable;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

abstract class BaseComparableTest<T extends Comparable<T>> {

    record ComparableTestCase<T>(T smallest, T middle, T greatest) {
        List<T> values() {
            return List.of(smallest, middle, greatest);
        }
    }

    abstract ComparableTestCase<T> getComparableTestCase();

    @Test
    void returnsZeroWhenComparingEqual() {
        getComparableTestCase().values()
                .forEach(value ->
                        assertThat(value.compareTo(value)).isZero()
                );
    }

    @Test
    void returnsPositiveWhenComparingToSmaller() {
        int result = getComparableTestCase().greatest().compareTo(getComparableTestCase().smallest());
        assertThat(result).isPositive();
    }

    @Test
    void returnsNegativeWhenComparingToGreater() {
        int result = getComparableTestCase().smallest().compareTo(getComparableTestCase().greatest());
        assertThat(result).isNegative();
    }

}
