package org.techtown.doitmission22.fragment;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.doitmission22.R;
import org.techtown.doitmission22.adapter.BookAdapter;
import org.techtown.doitmission22.helper.FragmentCallback;
import org.techtown.doitmission22.item.BookItem;

import java.util.ArrayList;

public class SearchFragment extends Fragment {
    private EditText title;
    private EditText author;
    private EditText content;

    private Context context;
    private FragmentCallback callback;
    private ArrayList<BookItem> items;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        this.context = context;
        if(context instanceof FragmentCallback) {
            callback = (FragmentCallback)context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_book, container, false);

        Bundle bundle = getArguments();
        if(bundle != null) {
            items = (ArrayList<BookItem>)bundle.getSerializable("BOOKITEMS");
        }

        RecyclerView bookView = (RecyclerView)rootView.findViewById(R.id.bookRecyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        bookView.setLayoutManager(manager);
        BookAdapter adpater = new BookAdapter(context);
        if(items != null) {
            adpater.setItems(items);
        }
        bookView.setAdapter(adpater);

        bookView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0,0,0,10);
            }
        });

        return rootView;
    }
}
