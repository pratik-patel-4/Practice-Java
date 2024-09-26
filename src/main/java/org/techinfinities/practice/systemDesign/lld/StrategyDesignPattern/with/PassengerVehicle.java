package org.techinfinities.practice.systemDesign.lld.StrategyDesignPattern.with;

import org.techinfinities.practice.systemDesign.lld.StrategyDesignPattern.with.Strategy.NormalDriveStrategy;

public class PassengerVehicle extends Vehicle {

    PassengerVehicle() {
        super(new NormalDriveStrategy());
    }

}
