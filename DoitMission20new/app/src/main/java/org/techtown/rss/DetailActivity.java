package org.techtown.rss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private TextView title;
    private TextView content;
    private TextView link;
    private TextView pubDate;
    private TextView author;

    private RssItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        title = (TextView)findViewById(R.id.title);
        content = (TextView)findViewById(R.id.content);
        link = (TextView)findViewById(R.id.link);
        pubDate = (TextView)findViewById(R.id.pubDate);
        author = (TextView)findViewById(R.id.author);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToMainActivity();
            }
        });

        Intent intent = getIntent();
        processIntent(intent);
    }

    private void processIntent(Intent intent) {
        if(intent != null) {
            item = (RssItem)intent.getSerializableExtra("RSSITEM");

            title.setText(item.title);
            content.setText(item.description);
            link.setText(item.link);
            pubDate.setText(item.pubDate);
            author.setText(item.author);
        }
    }

    private void returnToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP);

        startActivity(intent);
    }
}