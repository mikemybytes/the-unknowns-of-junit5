package com.mikemybytes.junit5.comparable;

class GameMap02Test implements ComparableContract<GameMap> {

    @Override
    public ComparableTestCase<GameMap> comparableTestCase() {
        return new ComparableTestCase<>(
                new GameMap("rookie", 1),
                new GameMap("private", 10),
                new GameMap("veteran", 999)
        );
    }

    // more tests here

}
