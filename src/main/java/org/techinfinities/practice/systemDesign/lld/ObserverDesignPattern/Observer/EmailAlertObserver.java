package org.techinfinities.practice.systemDesign.lld.ObserverDesignPattern.Observer;

import org.techinfinities.practice.systemDesign.lld.ObserverDesignPattern.Observable.StockObservable;

import java.util.Observable;
import java.util.Observer;

public class EmailAlertObserver implements NotificationAlertObserver {

    private StockObservable stockObservable;
    private String emailId;

    public EmailAlertObserver(String emailId, StockObservable stockObservable) {
        this.stockObservable = stockObservable;
        this.emailId = emailId;
    }


    @Override
    public void update() {
        sendEmails(emailId,"product is in stock hurry up !");
    }

    private void sendEmails(String email, String msg) {
        System.out.println("Mail Send to : " + email + " and message is : " + msg);
    }
}
