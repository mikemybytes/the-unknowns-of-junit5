package com.mikemybytes.junit5.comparable;

record Player(String name, int experience) implements Comparable<Player> {

    @Override
    public int compareTo(Player other) {
        return Integer.compare(this.experience, other.experience);
    }
}
