package com.mikemybytes.junit5.comparable;

record GameMap(String name, int difficulty) implements Comparable<GameMap> {

    @Override
    public int compareTo(GameMap other) {
        return Integer.compare(this.difficulty, other.difficulty);
    }

}
