package org.techinfinities.practice.systemDesign.lld.StrategyDesignPattern.with;

import org.techinfinities.practice.systemDesign.lld.StrategyDesignPattern.with.Strategy.DriveStrategy;

public class Vehicle {

    DriveStrategy driveStrategy;

    Vehicle(DriveStrategy driveStrategy) {
        this.driveStrategy = driveStrategy;
    }

    public void drive() {
        driveStrategy.drive();
    }
}
