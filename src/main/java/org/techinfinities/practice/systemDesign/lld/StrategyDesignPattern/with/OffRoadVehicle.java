package org.techinfinities.practice.systemDesign.lld.StrategyDesignPattern.with;

import org.techinfinities.practice.systemDesign.lld.StrategyDesignPattern.with.Strategy.SportsDriveStrategy;

public class OffRoadVehicle extends Vehicle {

    OffRoadVehicle() {
        super(new SportsDriveStrategy());
    }

}
