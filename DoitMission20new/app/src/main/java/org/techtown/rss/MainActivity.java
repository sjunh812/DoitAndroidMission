package org.techtown.rss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements RssCallback{
    private static final String RSSURL = "https://rss.joins.com/joins_homenews_list.xml";

    private ListView listView;
    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        adapter = new ListViewAdapter(this);
        listView.setAdapter(adapter);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request(RSSURL);
            }
        });
    }

    public void request(String urlStr) {
        adapter.clearItems();

        RssToXml task = new RssToXml(this);
        task.execute(urlStr);
    }

    @Override
    public void addItem(RssItem item) {
        adapter.addItem(item);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void returnToDetailActivity(RssItem item) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("RSSITEM", item);

        startActivity(intent);
    }
}