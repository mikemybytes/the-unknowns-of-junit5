package com.mikemybytes.junit5.comparable;

class GameMap01Test extends BaseComparableTest<GameMap> {

    @Override
    public ComparableTestCase<GameMap> getComparableTestCase() {
        return new ComparableTestCase<>(
                new GameMap("rookie", 1),
                new GameMap("private", 10),
                new GameMap("veteran", 999)
        );
    }

    // more tests here

}
