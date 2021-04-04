package org.techtown.doitmission22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.techtown.doitmission22.adapter.BookAdapter;
import org.techtown.doitmission22.fragment.InputFragment;
import org.techtown.doitmission22.fragment.SearchFragment;
import org.techtown.doitmission22.helper.AppHelper;
import org.techtown.doitmission22.helper.FragmentCallback;
import org.techtown.doitmission22.item.BookItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FragmentCallback {
    private BottomNavigationView bottomMenu;

    private AppHelper helper;
    private InputFragment inputFragment;
    private SearchFragment searchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new AppHelper(this);
        helper.initDB();
        helper.create(AppHelper.BOOK_TABLE);

        inputFragment = new InputFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, inputFragment).commit();
        searchFragment = new SearchFragment();

        bottomMenu = (BottomNavigationView)findViewById(R.id.bottomMenu);
        bottomMenu.setSelectedItemId(R.id.tab1);
        bottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                FragmentTransaction transaction;

                switch(id) {
                    case R.id.tab1:
                        transaction = getSupportFragmentManager().beginTransaction().replace(R.id.container, inputFragment);
                        transaction.commit();
                        break;
                    case R.id.tab2:
                        search();
                        transaction = getSupportFragmentManager().beginTransaction().replace(R.id.container, searchFragment);
                        transaction.commit();
                        //getSupportFragmentManager().popBackStack("flag2", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        break;
                    case R.id.tab3:
                    default:
                        break;
                }

                return true;
            }
        });
    }

    @Override
    public void save(Object[] objects) {
        helper.insert(AppHelper.BOOK_TABLE, objects);
    }

    public void search() {
        ArrayList<BookItem> items = helper.select();
        Bundle bundle = new Bundle();
        bundle.putSerializable("BOOKITEMS", items);
        searchFragment.setArguments(bundle);
    }

    @Override
    public void onBackPressed() {
        if(bottomMenu.getSelectedItemId() == R.id.tab1) {
            super.onBackPressed();
        } else {
            bottomMenu.setSelectedItemId(R.id.tab1);
        }
    }
}