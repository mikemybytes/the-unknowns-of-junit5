package com.mikemybytes.junit5.comparable;

class Player01Test extends BaseComparableTest<Player> {

    @Override
    public ComparableTestCase<Player> getComparableTestCase() {
        return new ComparableTestCase<>(
                new Player("player1", 1),
                new Player("player2", 25),
                new Player("player3", 141)
        );
    }

    // more tests here

}
