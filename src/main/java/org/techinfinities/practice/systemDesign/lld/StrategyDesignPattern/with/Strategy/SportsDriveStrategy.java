package org.techinfinities.practice.systemDesign.lld.StrategyDesignPattern.with.Strategy;

public class SportsDriveStrategy implements DriveStrategy {

    @Override
    public void drive() {
        System.out.println("Sports Drive Capabilities");
    }

}
