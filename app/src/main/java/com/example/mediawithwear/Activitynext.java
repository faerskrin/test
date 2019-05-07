package com.example.mediawithwear;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Activitynext extends AppCompatActivity {

    @BindView(R.id.toolbar_next)
    Toolbar toolbar;
    AdapterNext adapter;
    @BindView(R.id.recyc_next)
    RecyclerView recyc;
    SearchView searchView;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem sr = menu.findItem(R.id.search);
        searchView = (SearchView) sr.getActionView();
        searchView.setQueryHint("Поиск");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                List<Content> contents = new ArrayList<>();

                for (Content content: App.dm.getCont())
                {
                    String name = content.getNazv().toLowerCase();
                    if (name.contains(newText))
                    {
                        contents.add(content);
                    }

                }
                adapter.setSearch(contents);

                return false;
            }
        });
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        recyc.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterNext(App.dm.getCont());
        recyc.setAdapter(adapter);
    }
}
