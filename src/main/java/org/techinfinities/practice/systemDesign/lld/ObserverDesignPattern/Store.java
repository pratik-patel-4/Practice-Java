package org.techinfinities.practice.systemDesign.lld.ObserverDesignPattern;

import org.techinfinities.practice.systemDesign.lld.ObserverDesignPattern.Observable.IphoneStockObservable;
import org.techinfinities.practice.systemDesign.lld.ObserverDesignPattern.Observable.StockObservable;
import org.techinfinities.practice.systemDesign.lld.ObserverDesignPattern.Observer.EmailAlertObserver;
import org.techinfinities.practice.systemDesign.lld.ObserverDesignPattern.Observer.MobileAlertObserver;
import org.techinfinities.practice.systemDesign.lld.ObserverDesignPattern.Observer.NotificationAlertObserver;

public class Store {

    public static void main(String[] args) {

        StockObservable observable = new IphoneStockObservable();

        NotificationAlertObserver observer1 = new EmailAlertObserver("xyz@gmail.com", observable);
        NotificationAlertObserver observer2 = new EmailAlertObserver("abc@gmail.com", observable);
        NotificationAlertObserver observer3 = new MobileAlertObserver("Username1", observable);

        observable.add(observer1);
        observable.add(observer2);
        observable.add(observer3);

        observable.setStockCount(10);
        observable.setStockCount(0);
        observable.setStockCount(100);
    }

}
