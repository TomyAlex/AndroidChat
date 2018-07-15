package com.example.alex.chat;

import java.util.Random;

public class RandomUtils extends Random {
    // create random object
    private Random randomno;
    private Integer integer;
    // Constructor
    public RandomUtils(){
        this.randomno= new Random();
        this.integer = new Integer(0);
    }

    public Integer  randomInt(){
        this.integer = this.randomno.nextInt(2147483647);
        return this.integer;
    }
}
