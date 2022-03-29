package com.mikemybytes.junit5.comparable;

class PlayerTest implements ComparableContract<Player> {

    @Override
    public ComparableTestCase<Player> comparableTestCase() {
        return new ComparableTestCase<>(
                new Player("player1", 1),
                new Player("player2", 25),
                new Player("player3", 141)
        );
    }

    // more tests here

}
