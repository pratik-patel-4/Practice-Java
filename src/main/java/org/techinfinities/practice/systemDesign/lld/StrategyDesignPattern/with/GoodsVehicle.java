package org.techinfinities.practice.systemDesign.lld.StrategyDesignPattern.with;

import org.techinfinities.practice.systemDesign.lld.StrategyDesignPattern.with.Strategy.NormalDriveStrategy;

public class GoodsVehicle extends Vehicle {

    GoodsVehicle() {
        super(new NormalDriveStrategy());
    }
}
