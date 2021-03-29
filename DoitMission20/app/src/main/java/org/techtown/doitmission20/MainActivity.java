package org.techtown.doitmission20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity implements RssCallback{
    private static final String URL = "https://rss.joins.com/joins_news_list.xml";

    private GridView gridView;
    private GridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView)findViewById(R.id.gridView);
        adapter = new GridAdapter(this);
        gridView.setAdapter(adapter);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request(URL);
            }
        });
    }

    private void request(String url) {
        RssToXml task = new RssToXml(this);
        adapter.initItems();
        task.execute(url);
    }

    @Override
    public void setUI(RssItem item) {
        adapter.addItem(item);
        adapter.notifyDataSetChanged();
    }
}