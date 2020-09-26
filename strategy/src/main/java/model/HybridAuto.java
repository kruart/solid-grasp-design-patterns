package model;

import strategy.HybridFillStrategy;

public class HybridAuto extends Auto {
    public HybridAuto() {
        super(new HybridFillStrategy());
    }
}
