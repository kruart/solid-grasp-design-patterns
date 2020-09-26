package model;

import strategy.StandardFillStrategy;

public class Sedan extends Auto {
    public Sedan() {
        super(new StandardFillStrategy());
    }
}
