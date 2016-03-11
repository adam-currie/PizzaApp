package com.github.adam_currie.pizzaapp.data;

/**
 * Created by Adam on 2016-03-10.
 */
public class Side {
    private String name;
    private long price;//in cents
    private String detail;

    public Side(String name, long price, String detail){
        this.name = name;
        this.price = price;
        this.detail = detail;
    }

    public String getName(){
        return name;
    }

    public String getDetail(){
        return detail;
    }

    public long getPrice(){
        return price;
    }

    public String getPriceString() {
        return "$" + price/100 + "." + price%100;
    }
}
