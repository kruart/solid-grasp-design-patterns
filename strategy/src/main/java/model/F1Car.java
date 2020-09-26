package model;

import strategy.F1PitstopStrategy;

public class F1Car extends Auto {
    public F1Car() {
        super(new F1PitstopStrategy());
    }
}
