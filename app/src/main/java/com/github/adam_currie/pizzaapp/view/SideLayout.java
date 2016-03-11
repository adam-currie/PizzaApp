package com.github.adam_currie.pizzaapp.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.adam_currie.pizzaapp.R;
import com.github.adam_currie.pizzaapp.data.Order;
import com.github.adam_currie.pizzaapp.data.Side;
import com.github.adam_currie.pizzaapp.view.activities.SideDetailActivity;

/**
 * Created by Adam on 2016-03-10.
 */
public class SideLayout extends RelativeLayout implements OnClickListener {
    private final Order order;
    private Side side;
    private Context context;

    public SideLayout(Context context) {
        super(context);
        order = null;
        this.context = context;
    }

    public SideLayout(Context context, Order order , Side side) {
        super(context);
        this.order = order;
        this.side = side;
        this.context = context;

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_side_list_item, this, true);

        findViewById(R.id.checkBox).setOnClickListener(this);
        this.setOnClickListener(this);

        setSide(side);
    }

    @Override
    public void onClick(View v) {
        if(v == findViewById(R.id.checkBox)){
            if(order != null) {
                if (((CheckBox) findViewById(R.id.checkBox)).isChecked()) {
                    //add to order
                    order.getSides().add(side);
                } else {
                    //remove from order
                    order.removeSideByName(side.getName());
                }
            }
        }else{
            //goto details activity
            Intent intent = new Intent(context, SideDetailActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("sideName", side.getName());
            intent.putExtra("sidePrice", side.getPriceString());
            intent.putExtra("sideDetail", side.getDetail());
            context.startActivity(intent);
        }
    }

    public void setSide(Side side) {
        //name
        TextView nameText = (TextView)findViewById(R.id.nameText);
        nameText.setText(side.getName());

        //price
        TextView priceText = (TextView)findViewById(R.id.priceText);
        priceText.setText(side.getPriceString());
    }
}
