package org.techtown.rss;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ListViewLayout extends LinearLayout {
    private TextView index;
    private TextView title;

    public ListViewLayout(Context context) {
        super(context);
        init(context);
    }

    public ListViewLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_list_view, this, true);

        index = (TextView)findViewById(R.id.indexTextView);
        title = (TextView)findViewById(R.id.textView);
    }

    public void setIndex(int index) {
        this.index.setText("#" + index);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }
}
