package com.github.adam_currie.pizzaapp;

import android.app.Application;

import com.github.adam_currie.pizzaapp.data.Order;
import com.github.adam_currie.pizzaapp.data.PizzaDB;

/**
 * Created by Adam on 2016-03-10.
 */
public class PizzaApp extends Application{
    private PizzaDB db;
    private Order order;

    @Override
    public void onCreate() {
        super.onCreate();
        db = new PizzaDB(getApplicationContext());
        order = new Order();
    }

    public PizzaDB getDb(){
        return db;
    }

    public Order getOrder() {
        return order;
    }

}
