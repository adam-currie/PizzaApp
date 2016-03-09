package com.github.adam_currie.pizzaapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
            Intent intent = new Intent(this, ConfirmActivity.class);

            //toppings
            CheckBox pepperoni = (CheckBox)findViewById(R.id.pepperoniCheckBox);
            intent.putExtra("pepperoni", pepperoni.isChecked());
            CheckBox mushroom = (CheckBox)findViewById(R.id.mushroomCheckBox);
            intent.putExtra("mushroom", mushroom.isChecked());
            CheckBox olives = (CheckBox)findViewById(R.id.olivesCheckBox);
            intent.putExtra("olives", olives.isChecked());
            CheckBox pineapple = (CheckBox)findViewById(R.id.pineappleCheckBox);
            intent.putExtra("pineapple", pineapple.isChecked());
            CheckBox peppers = (CheckBox)findViewById(R.id.peppersCheckBox);
            intent.putExtra("peppers", peppers.isChecked());
            CheckBox bacon = (CheckBox)findViewById(R.id.baconCheckBox);
            intent.putExtra("bacon", bacon.isChecked());

            //size
            RadioGroup sizeRadGroup = (RadioGroup)findViewById(R.id.sizeRadioGroup);
            RadioButton sizeRadBtn = (RadioButton)findViewById(sizeRadGroup.getCheckedRadioButtonId());
            intent.putExtra("size", sizeRadBtn.getText());

            //quantity
            NumberPicker quantity = (NumberPicker)findViewById(R.id.quantityPicker);
            intent.putExtra("quantity", quantity.getValue());

            startActivity(intent);
        }else if(v == findViewById(R.id.backFromOrderButton)){
            super.finish();//goes back to starter of this activity
        }
    }

}
