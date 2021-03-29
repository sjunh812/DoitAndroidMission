package org.techtown.mission11;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public interface FragmentCallback {
    public void managementAdapter(ViewPager pager);
    public void onDetachFragment();
}
