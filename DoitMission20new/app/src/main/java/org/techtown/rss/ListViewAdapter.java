package org.techtown.rss;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter{
    private ArrayList<RssItem> items = new ArrayList<>();
    private RssCallback callback;
    private Context context;

    public ListViewAdapter(Context context) {
        this.context = context;

        if(context instanceof RssCallback) {
            callback = (RssCallback)context;
        }
    }

    public void clearItems() {
        items.clear();
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
        ListViewLayout view = null;

        if(convertView == null) {
            view = new ListViewLayout(context);
        } else {
            view = (ListViewLayout)convertView;
        }

        RssItem item = items.get(position);
        view.setIndex(position + 1);
        view.setTitle(item.title);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.returnToDetailActivity(item);
            }
        });

        return view;
    }
}
