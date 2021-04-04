package org.techtown.doitmission22.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.techtown.doitmission22.R;
import org.techtown.doitmission22.helper.FragmentCallback;

public class InputFragment extends Fragment {
    private EditText title;
    private EditText author;
    private EditText content;

    private Context context;
    private FragmentCallback callback;

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
        View rootView = inflater.inflate(R.layout.fragment_input_book, container, false);

        title = (EditText)rootView.findViewById(R.id.titleEditText);
        author = (EditText)rootView.findViewById(R.id.authorEditText);
        content = (EditText)rootView.findViewById(R.id.contentEditText);

        Button saveButton = (Button)rootView.findViewById(R.id.button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object[] objects = {title.getText().toString(), author.getText().toString(), content.getText().toString()};
                callback.save(objects);

                title.setText("");
                author.setText("");
                content.setText("");
                title.requestFocus();
            }
        });

        return rootView;
    }
}
