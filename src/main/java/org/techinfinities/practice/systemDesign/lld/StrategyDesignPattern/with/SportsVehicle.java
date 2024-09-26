package org.techinfinities.practice.systemDesign.lld.StrategyDesignPattern.with;

import org.techinfinities.practice.systemDesign.lld.StrategyDesignPattern.with.Strategy.SportsDriveStrategy;

public class SportsVehicle extends Vehicle {
    SportsVehicle() {
        super(new SportsDriveStrategy());
    }
}
