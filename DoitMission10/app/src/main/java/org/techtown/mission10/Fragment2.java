package org.techtown.mission10;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Field;

public class Fragment2 extends Fragment {
    private MainActivity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (MainActivity)getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.fragment2, container, false);

        ViewPager pager = (ViewPager)view.findViewById(R.id.pager2);
        MainActivity.MyAdapter adapter = new MainActivity.MyAdapter(getActivity().getSupportFragmentManager());

        adapter.addItem(activity.innerFragment1);
        adapter.addItem(activity.innerFragment2);

        pager.setAdapter(adapter);
        return view;
    }

}
