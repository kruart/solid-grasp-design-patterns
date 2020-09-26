package model;

import strategy.FillStrategy;

public class Auto {
    private FillStrategy fillStrategy;

    public Auto(FillStrategy fillStrategy) {
        this.fillStrategy = fillStrategy;
    }

    public void fill() {
        this.fillStrategy.fill();
    }

    public void gas() {
        System.out.println("Go ahead!");
    }

    public void stop() {
        System.out.println("Slow down!");
    }
}
