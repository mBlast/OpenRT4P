package com.example.application.Activité_n2.Order;

public abstract class Order {
    private String name;
    private int id;

    public int getId() {
        return id;
    }
    Order(){

    }

    public String getType(){
        return this.getClass().getSimpleName();
    }

    public abstract String createDatagramme();
}
