package org.techtown.doitmission20;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    private ArrayList<RssItem> items = new ArrayList<>();
    private Context context;

    public void initItems() {
        items = new ArrayList<>();
    }

    public GridAdapter(Context context) {
        this.context = context;
    }

    public void addItem(RssItem item) {
        items.add(item);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GridLayout view = null;

        if(convertView == null) {
            view = new GridLayout(context);
        } else {
            view = (GridLayout)convertView;
        }

        RssItem item = items.get(position);

        view.setTitle(item.title);
        view.setContent(item.description);

        return view;
    }
}
