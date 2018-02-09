package client.models;

import java.util.ArrayList;

public class PlayerModel implements IModel {
    private String name = "";
//    private ArrayList<Observer> observers = new ArrayList<>();
//
//    public void addObserver(Observer observer) {
//        observers.add(observer);
//    }

//    public void dispatchLoginSuccess() {
//        for (Observer observer : observers) observer.onLoginSuccess(this);
//    }
//
//    public void dispatchRegistrationSuccess() {
//        for (Observer observer : observers) observer.onRegistrationSuccess(this);
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public void removeObserver(Observer observer) {
//        observers.remove(observer);
//    }

//    public interface Observer {
//        void onLoginSuccess(PlayerModel player);
//        void onRegistrationSuccess(PlayerModel player);
//    }
}
