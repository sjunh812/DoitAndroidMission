package org.techtown.doitmission20;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class GridLayout extends LinearLayout {
    private TextView title;
    private TextView content;

    public GridLayout(Context context) {
        super(context);
        init(context);
    }

    public GridLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_grid_view, this, true);

        title = (TextView)findViewById(R.id.titleTextView);
        content = (TextView)findViewById(R.id.contentTextView);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setContent(String content) {
        this.content.setText(content);
    }
}
