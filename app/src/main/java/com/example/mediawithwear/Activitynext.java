package com.example.mediawithwear;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediawithwear.ui.tab.Activitytab;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activitynext extends AppCompatActivity {

    @BindView(R.id.toolbar_next)
    Toolbar toolbar;
    AdapterNext adapter;
    @BindView(R.id.recyc_next)
    RecyclerView recyc;
    SearchView searchView;
    @BindView(R.id.checkbox_next)
    CheckBox checkBox;

    SharedPreferences sp;




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

    @OnClick(R.id.button_tab)
    void doTab(){
        Intent intent = new Intent(this, Activitytab.class);
        startActivity(intent);
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

        sp = getSharedPreferences("MY_DATA", Context.MODE_PRIVATE);
        Boolean state = sp.getBoolean("STATE", false);


        if (state)
        {
            checkBox.setChecked(true);
        }
        else  {checkBox.setChecked(false);}


        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkBox.isChecked()) {
                    startService(new Intent(Activitynext.this, Alarm.class));
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("STATE",true);
                    editor.apply();
                }
                else {
                    stopService(new Intent(Activitynext.this, Alarm.class));
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("STATE",false);
                    editor.apply();
                }
            }
        });




    }
}
