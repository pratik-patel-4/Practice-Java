package org.techinfinities.practice.systemDesign.lld.ObserverDesignPattern.Observer;

import org.techinfinities.practice.systemDesign.lld.ObserverDesignPattern.Observable.StockObservable;

import java.util.Observer;

public class MobileAlertObserver implements NotificationAlertObserver {

    private String userName;
    private StockObservable observable;

    public MobileAlertObserver(String userName, StockObservable observable) {
        this.userName = userName;
        this.observable = observable;
    }

    @Override
    public void update() {
        sendMsgOnMobile(userName, "Product is in stock hurry up!");
    }

    private void sendMsgOnMobile(String userName, String s) {
        System.out.println("Message sent to " + userName + " : " + s);
    }
}
