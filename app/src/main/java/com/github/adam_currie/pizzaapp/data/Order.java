
package com.github.adam_currie.pizzaapp.data;

import com.github.adam_currie.pizzaapp.data.Side;
import java.util.ArrayList;

/**
 * Created by Adam on 2016-03-10.
 */
public class Order {
    private static ArrayList<Side> sides = new ArrayList<Side>();
    private static ArrayList<String> toppings = new ArrayList<String>();
    private static int pizzaQuantity = 1;
    private static String pizzaSize;

    public String getSize(){
        return pizzaSize;
    }

    public void setSize(String size){
        pizzaSize = size;
    }

    public int getQuantity(){
        return pizzaQuantity;
    }

    public void setQuantity(int quantity){
        pizzaQuantity = quantity;
    }

    public ArrayList<Side> getSides(){
        return sides;
    }

    public ArrayList<String> getToppings(){
        return toppings;
    }

    public void removeSideByName(String name) {
        for (Side side : sides) {
            if(side.getName() == name){
                sides.remove(side);
                break;
            }
        }
    }
}
