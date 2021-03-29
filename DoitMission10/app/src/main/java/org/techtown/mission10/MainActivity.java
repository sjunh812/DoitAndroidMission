package org.techtown.mission10;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentCallBack{
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;

    public InnerFragment1 innerFragment1;
    public InnerFragment2 innerFragment2;

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private BottomNavigationView bottom;

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        innerFragment1 = new InnerFragment1();
        innerFragment2 = new InnerFragment2();

        //getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();

        viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(3);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());

        adapter.addItem(fragment1);
        adapter.addItem(fragment2);
        adapter.addItem(fragment3);

        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch(position) {
                    case 0:
                        bottom.setSelectedItemId(R.id.tab1);
                        break;
                    case 1:
                        bottom.setSelectedItemId(R.id.tab2);
                        break;
                    case 2:
                        bottom.setSelectedItemId(R.id.tab3);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottom = (BottomNavigationView)findViewById(R.id.bottomTab);
        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.tab1) {
                    Toast.makeText(getApplicationContext(), "1번", Toast.LENGTH_SHORT).show();
                    onChangeFragment(0, null);
                }
                else if(id == R.id.tab2) {
                    onChangeFragment(1, null);
                }
                else if(id == R.id.tab3) {
                    onChangeFragment(2, null);
                }

                return true;
            }
        });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        bottom.setSelectedItemId(R.id.tab1);
    }

    static class MyAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<>();

        public MyAdapter(@NonNull FragmentManager fm) {
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

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.nav_0) {
            bottom.setSelectedItemId(R.id.tab1);
            toolbar.setTitle("사진, 동영상");
        }
        else if(id == R.id.nav_1) {
            bottom.setSelectedItemId(R.id.tab2);
            toolbar.setTitle("파일");
        }
        else if(id == R.id.nav_2) {
            bottom.setSelectedItemId(R.id.tab3);
            toolbar.setTitle("링크");
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onChangeFragment(int position, Bundle bundle) {
        Fragment currentFragment = null;

        switch(position) {
            case 0:
                viewPager.setCurrentItem(0);
                currentFragment = fragment1;
                break;
            case 1:
                viewPager.setCurrentItem(1);
                currentFragment = fragment2;
                break;
            case 2:
                viewPager.setCurrentItem(2);
                currentFragment = fragment3;
                break;
            default:
                break;
        }

        if(currentFragment != null) {
            //getSupportFragmentManager().beginTransaction().replace(R.id.container, currentFragment).commit();
        }
    }
}