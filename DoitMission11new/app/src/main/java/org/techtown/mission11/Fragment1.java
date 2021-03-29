package org.techtown.mission11;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class Fragment1 extends Fragment {
    FragmentCallback callback;
    public PagerFragment1 pagerFragment1;
    public PagerFragment2 pagerFragment2;

    ViewPager pager;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof FragmentCallback) {
            callback = (FragmentCallback)context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Toast.makeText(getContext(), "onDetach()",Toast.LENGTH_LONG).show();

        if(callback != null) {
            callback = null;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getContext(), "conCreatView()",Toast.LENGTH_LONG).show();
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment1, container, false);

        ViewPager pager = (ViewPager)rootView.findViewById(R.id.viewPager);
        pagerFragment1 = new PagerFragment1();
        pagerFragment2 = new PagerFragment2();

        PagerAdapter adapter = new PagerAdapter(getChildFragmentManager());

        adapter.addItem(pagerFragment1);
        adapter.addItem(pagerFragment2);

        pager.setAdapter(adapter);
        //callback.managementAdapter(pager);

        return rootView;
    }

    class PagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<>();

        public PagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment item) {
            items.add(item);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }
}
