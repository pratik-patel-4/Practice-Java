package org.techinfinities.practice.systemDesign.lld.ObserverDesignPattern.Observable;

import org.techinfinities.practice.systemDesign.lld.ObserverDesignPattern.Observer.NotificationAlertObserver;

public interface StockObservable {

    public void add(NotificationAlertObserver displayObserver);

    public void remove(NotificationAlertObserver displayObserver);

    public void notifyObservers();

    public void setStockCount(int count);

    public int getStockCount();
}
