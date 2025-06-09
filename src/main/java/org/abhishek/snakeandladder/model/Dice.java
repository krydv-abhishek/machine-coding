package org.abhishek.snakeandladder.model;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {

    private final int min;
    private final int max;

    public Dice(int count) {
        min = count;
        max = count * 6;
    }

    public int roll(Player player) {
        int number = getDiceRollVal();

        while (number % 6 == 0) {
            System.out.println(player.getName() + " got a Six. So rolling again");
            number += getDiceRollVal();
            if (number % 6 > 0 && number / 6==2) {
               break;
            }

            if (number % 6 == 0) {
                if(number/6>2) {
                    number= getDiceRollVal();
                }
            }
        }
        return number;
    }

    private int getDiceRollVal() {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}