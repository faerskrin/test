package com.example.mediawithwear.ui.tab;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.mediawithwear.AdapterNext;
import com.example.mediawithwear.App;
import com.example.mediawithwear.Content;
import com.example.mediawithwear.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Activitytab extends AppCompatActivity {

    @BindView(R.id.toolbar_tab)
    Toolbar toolbar;
    SearchView searchView;
    AdapterNext adapter;
    @BindView(R.id.view_pager)
    ViewPager vp;
    @BindView(R.id.tabs)
    TabLayout tabs;

        List<String> Groups = new ArrayList<>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem sr = menu.findItem(R.id.search);
        searchView = (SearchView) sr.getActionView();
        searchView.setQueryHint("Поиск. . .");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                List<Content> c1 = new ArrayList<>();

                for (Content content: App.dm.getCont())
                {
                    String name = content.getNazv();
                    if (name.contains(newText))
                    {
                        c1.add(content);
                    }
                }
              // adapter.setSearch(c1);
                return false;
            }
        });

        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Groups.add("Первый");
        Groups.add("Второй");
        Groups.add("Третий");
        vp.setAdapter(new GroupVp(getSupportFragmentManager(),Groups));
        tabs.setupWithViewPager(vp);
    }
}
