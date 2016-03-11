package com.github.adam_currie.pizzaapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Adam on 2016-03-10.
 */
public class SideListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Side> sides;
    private Order order;

    public SideListAdapter(Context context, ArrayList<Side> sides, Order order){
        this.context = context;
        this.sides = sides;
        this.order = order;
    }

    @Override
    public int getCount() {
        return sides.size();
    }

    @Override
    public Object getItem(int position) {
        return sides.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Side side = sides.get(position);

        if (convertView == null) {
            return new SideLayout(
                    context,
                    order,
                    side
            );
        }

        SideLayout sideLayout = (SideLayout) convertView;
        sideLayout.setSide(side);
        return sideLayout;
    }

}
