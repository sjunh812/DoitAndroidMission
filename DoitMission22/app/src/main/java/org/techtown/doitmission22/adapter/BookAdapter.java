package org.techtown.doitmission22.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.doitmission22.R;
import org.techtown.doitmission22.item.BookItem;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{
    private ArrayList<BookItem> items = new ArrayList<>();
    private Context context;
    OnItemClickListener listener;

    public static interface OnItemClickListener {
        public void onItemClick(ViewHolder holder, View view, int position);
    }

    public BookAdapter(Context context) {
        this.context = context;
    }

    public void addItem(BookItem item) {
        items.add(item);
    }

    public void setItems(ArrayList<BookItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_book, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookItem item = items.get(position);

        holder.setText(new StringBuilder("#").append(position + 1).append(" ").append(item.getTitle()).toString(), item.getAuthor());
        holder.setOnItemClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView author;
        private OnItemClickListener listener;
        private int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            position = getAdapterPosition();
            title = (TextView)itemView.findViewById(R.id.titleTextView);
            author = (TextView)itemView.findViewById(R.id.authorTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(ViewHolder.this, v, position);
                }
            });
        }

        public void setText(String title, String author) {
            this.title.setText(title);
            this.author.setText(author);
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }
    }
}
