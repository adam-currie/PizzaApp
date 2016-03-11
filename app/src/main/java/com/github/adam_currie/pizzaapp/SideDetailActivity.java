package com.github.adam_currie.pizzaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SideDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_detail);

        //get side info
        String name = getIntent().getStringExtra("sideName");
        String price = getIntent().getStringExtra("sidePrice");
        String detail = getIntent().getStringExtra("sideDetail");

        //set text views
        ((TextView)findViewById(R.id.nameText)).setText(name);
        ((TextView)findViewById(R.id.priceText)).setText(price);
        ((TextView)findViewById(R.id.detailText)).setText(detail);

        //todo populate fields
    }
}
