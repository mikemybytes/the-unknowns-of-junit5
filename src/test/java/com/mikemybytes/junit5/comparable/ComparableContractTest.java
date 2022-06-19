package com.mikemybytes.junit5.comparable;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Named.named;

public class ComparableContractTest {

    record ComparableTestCase<T extends Comparable<T>>(T smallest, T middle, T greatest) {
        List<T> values() {
            return List.of(smallest, middle, greatest);
        }
    }

    private static ComparableTestCase<Player> playerTestCase() {
        return new ComparableTestCase<>(
                new Player("one", 5), new Player("two", 17), new Player("three", 25)
        );
    }

    private static ComparableTestCase<GameMap> gameMapTestCase() {
        return new ComparableTestCase<>(
                new GameMap("easy", 1), new GameMap("mid", 2), new GameMap("hard", 999)
        );
    }

    static Stream<Named<ComparableTestCase<?>>> comparableTestCases() { // parameterize class under test!
        return Stream.of(
                named(GameMap.class.getSimpleName() + " obeys contract", gameMapTestCase()),
                named(Player.class.getSimpleName() + " obeys contract", playerTestCase())
        );
    }

    @ParameterizedTest
    @MethodSource("comparableTestCases")
    <T extends Comparable<T>> void returnsZeroWhenComparingEqual(ComparableTestCase<T> testCase) {
        testCase.values()
                .forEach(value ->
                        assertThat(value.compareTo(value)).isZero()
                );
    }

    @ParameterizedTest
    @MethodSource("comparableTestCases")
    <T extends Comparable<T>> void returnsPositiveWhenComparingToSmaller(ComparableTestCase<T> testCase) {
        int result = testCase.greatest.compareTo(testCase.smallest());
        assertThat(result).isPositive();
    }

    @ParameterizedTest
    @MethodSource("comparableTestCases")
    <T extends Comparable<T>> void returnsNegativeWhenComparingToGreater(ComparableTestCase<T> testCase) {
        int result = testCase.smallest().compareTo(testCase.greatest());
        assertThat(result).isNegative();
    }

}
