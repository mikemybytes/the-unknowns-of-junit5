package com.mikemybytes.junit5.comparable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

interface ComparableContract<T extends Comparable<T>> {

    record ComparableTestCase<T>(T smallest, T middle, T greatest) {
        List<T> values() {
            return List.of(smallest, middle, greatest);
        }
    }

    ComparableTestCase<T> comparableTestCase();

    private T greatest() {
        return comparableTestCase().greatest();
    }

    private T smallest() {
        return comparableTestCase().smallest();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    default void returnsZeroWhenComparingEqual(int index) {
        T input = comparableTestCase().values().get(index);
        assertThat(input.compareTo(input)).isZero();
    }

    @Test
    default void returnsPositiveWhenComparingToSmaller() {
        int result = greatest().compareTo(smallest());
        assertThat(result).isPositive();
    }

    @Test
    default void returnsNegativeWhenComparingToGreater() {
        int result = smallest().compareTo(greatest());
        assertThat(result).isNegative();
    }

}
