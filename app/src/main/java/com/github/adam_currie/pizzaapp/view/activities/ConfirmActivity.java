package com.github.adam_currie.pizzaapp.view.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.view.View;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.util.Log;

import com.github.adam_currie.pizzaapp.PizzaApp;
import com.github.adam_currie.pizzaapp.R;
import com.github.adam_currie.pizzaapp.data.Order;
import com.github.adam_currie.pizzaapp.data.Side;
import com.github.adam_currie.pizzaapp.file.SaveToFileTask;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;

public class ConfirmActivity extends AppCompatActivity implements View.OnClickListener {
    private TableLayout priceTable;
    private static final int CHOOSE_SAVE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_confirm);

        findViewById(R.id.confirmButton).setOnClickListener(this);
        findViewById(R.id.saveReceiptButton).setOnClickListener(this);
        findViewById(R.id.backFromConfirmButton).setOnClickListener(this);

        priceTable = (TableLayout)findViewById(R.id.priceTable);

        double total = 0;

        Order order = ((PizzaApp)getApplication()).getOrder();

        //size
        Log.i("debug", "pizza size: " + order.getSize());
        if(order.getSize().equals("Small")){
            addToTable("small pizza", "$5.99");
            total += 5.99;
        }else if(order.getSize().equals("Medium")){
            addToTable("medium pizza", "$7.99");
            total += 7.99;
        }else if(order.getSize().equals("Large")){
            addToTable("large pizza", "$9.99");
            total += 9.99;
        }else if(order.getSize().equals("Extra Large")){
            addToTable("xl pizza", "$11.99");
            total += 11.99;
        }

        //toppings
        for (String topping : order.getToppings()) {
            addToTable(topping, "$0.50");
            total += .50;
        }

        //quantity
        Log.i("debug", "pizza quantity: " + order.getQuantity());
        total *= order.getQuantity();

        //sides
        for(Side side : order.getSides()){
            addToTable(side.getName(), side.getPriceString());
            total += (double)side.getPrice()/100;
        }

        //total
        addToTable("total", "$" + new DecimalFormat("#.##").format(total));

    }

    private void addToTable(String item, String price) {
        TableRow row = new TableRow(this);
        row.setLayoutParams(new ViewGroup.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT
        ));

        TextView itemTextView = new TextView(this);
        itemTextView.setText(item);

        TextView priceTextView = new TextView(this);
        priceTextView.setText(price);

        LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1.0f
        );

        itemTextView.setLayoutParams(params);
        row.addView(itemTextView);

        priceTextView.setLayoutParams(params);
        row.addView(priceTextView);

        priceTable.addView(row);
    }

    @Override
    public void onClick(View v) {
        if(v == findViewById(R.id.confirmButton)){
            Intent intent = new Intent(this, GeneralInfoActivity.class);
            //setup intent to clear history when going to main activity
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }else if(v == findViewById(R.id.backFromConfirmButton)){
            super.finish();//goes back to starter of this activity
        }else if(v == findViewById(R.id.saveReceiptButton)){
            Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TITLE, "receipt.txt");

            startActivityForResult(intent, CHOOSE_SAVE_REQUEST);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        if(requestCode == CHOOSE_SAVE_REQUEST){
            if(resultCode == RESULT_OK){
                Log.i("debug", "saving receipt: " + intent.getDataString());

                Uri uri = Uri.parse(intent.getDataString());
                try {
                    new SaveToFileTask().execute(
                            getContentResolver().openOutputStream(uri, "w"),
                            "todo: replace with real text",
                            getApplicationContext()
                    );
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

