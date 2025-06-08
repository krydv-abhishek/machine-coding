package org.abhishek.snakeandladder.model;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {

    private final int min;
    private final int max;

    public Dice(int count) {
        min = count;
        max = count * 6;
    }

    public int roll() {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
