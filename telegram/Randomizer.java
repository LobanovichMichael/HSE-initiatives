package ru.misha.telegram;

import java.util.Random;

public class Randomizer {

    public static int getRandomInt (int minRange,int maxRange) {
        int randomNumber;
        int range = maxRange-minRange;
        Random random = new Random();
        randomNumber = random.nextInt(range);
        randomNumber += minRange;
        return randomNumber;
    }
}
