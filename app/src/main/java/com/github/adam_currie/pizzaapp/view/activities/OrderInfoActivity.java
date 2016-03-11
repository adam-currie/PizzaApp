package com.github.adam_currie.pizzaapp.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.github.adam_currie.pizzaapp.PizzaApp;
import com.github.adam_currie.pizzaapp.R;
import com.github.adam_currie.pizzaapp.data.Order;

public class OrderInfoActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_order_info);

        NumberPicker np = (NumberPicker) findViewById(R.id.quantityPicker);
        np.setMaxValue(5);
        np.setMinValue(1);
        np.setValue(1);

        findViewById(R.id.orderButton).setOnClickListener(this);
        findViewById(R.id.backFromOrderButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == findViewById(R.id.orderButton)){
            Order order = ((PizzaApp)getApplication()).getOrder();

            //size
            RadioGroup sizeRadGroup = (RadioGroup)findViewById(R.id.sizeRadioGroup);
            RadioButton sizeRadBtn = (RadioButton)findViewById(sizeRadGroup.getCheckedRadioButtonId());
            order.setSize((String)sizeRadBtn.getText());

            //toppings
            order.getToppings().clear();
            CheckBox pepperoni = (CheckBox)findViewById(R.id.pepperoniCheckBox);
            if(pepperoni.isChecked()){
                order.getToppings().add("pepperoni");
            }
            CheckBox mushroom = (CheckBox)findViewById(R.id.mushroomCheckBox);
            if(mushroom.isChecked()){
                order.getToppings().add("mushroom");
            }
            CheckBox olives = (CheckBox)findViewById(R.id.olivesCheckBox);
            if(olives.isChecked()){
                order.getToppings().add("olives");
            }
            CheckBox pineapple = (CheckBox)findViewById(R.id.pineappleCheckBox);
            if(pineapple.isChecked()){
                order.getToppings().add("pineapple");
            }
            CheckBox peppers = (CheckBox)findViewById(R.id.peppersCheckBox);
            if(peppers.isChecked()){
                order.getToppings().add("peppers");
            }
            CheckBox bacon = (CheckBox)findViewById(R.id.baconCheckBox);
            if(bacon.isChecked()){
                order.getToppings().add("bacon");
            }

            //quantity
            NumberPicker quantity = (NumberPicker)findViewById(R.id.quantityPicker);
            order.setQuantity(quantity.getValue());

            Intent intent = new Intent(this, SidesOrderActivity.class);
            startActivity(intent);
        }else if(v == findViewById(R.id.backFromOrderButton)){
            super.finish();//goes back to starter of this activity
        }
    }

}
