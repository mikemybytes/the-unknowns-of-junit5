package com.mikemybytes.junit5.comparable;

record Player(String name, int rank) implements Comparable<Player> {

    @Override
    public int compareTo(Player other) {
        return Integer.compare(this.rank, other.rank);
    }
}
