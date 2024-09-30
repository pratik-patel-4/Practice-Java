package org.techinfinities.practice.systemDesign.lld.ObserverDesignPattern.Observable;

import org.techinfinities.practice.systemDesign.lld.ObserverDesignPattern.Observer.NotificationAlertObserver;

import java.util.ArrayList;
import java.util.List;

public class IphoneStockObservable implements StockObservable {

    List<NotificationAlertObserver> observers = new ArrayList<NotificationAlertObserver>();
    private int stockCount = 0;

    @Override
    public void add(NotificationAlertObserver displayObserver) {
        observers.add(displayObserver);
    }

    @Override
    public void remove(NotificationAlertObserver displayObserver) {
        observers.remove(displayObserver);
    }

    @Override
    public void notifyObservers() {
        for (NotificationAlertObserver observer : observers) {
            observer.update();
        }
    }

    @Override
    public void setStockCount(int count) {
        if(stockCount == 0) {
            notifyObservers();
        }
        stockCount = count;
    }

    @Override
    public int getStockCount() {
        return stockCount;
    }
}
