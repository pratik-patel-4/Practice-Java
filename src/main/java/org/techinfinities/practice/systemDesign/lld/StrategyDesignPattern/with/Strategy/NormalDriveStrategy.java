package org.techinfinities.practice.systemDesign.lld.StrategyDesignPattern.with.Strategy;

public class NormalDriveStrategy implements DriveStrategy {

    @Override
    public void drive() {
        System.out.println("Normal Drive Capabilities");
    }
}
