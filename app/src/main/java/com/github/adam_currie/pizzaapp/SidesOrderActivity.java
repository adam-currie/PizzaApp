package com.github.adam_currie.pizzaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class SidesOrderActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sides_order);

        //add listeners
        findViewById(R.id.orderButton).setOnClickListener(this);
        findViewById(R.id.backFromOrderButton).setOnClickListener(this);

        //fill side list
        PizzaDB db = ((PizzaApp)getApplication()).getDb();

        ArrayList<Side> sides = db.getSides();
        SideListAdapter adapter = new SideListAdapter(
                getApplicationContext(),
                sides,
                ((PizzaApp)getApplication()).getOrder()
        );
        ((ListView)findViewById(R.id.sideList)).setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if(v == findViewById(R.id.orderButton)) {
            Intent intent = new Intent(this, ConfirmActivity.class);
            startActivity(intent);
        }else if(v == findViewById(R.id.backFromOrderButton)){
            super.finish();//goes back to starter of this activity
        }
    }
}
