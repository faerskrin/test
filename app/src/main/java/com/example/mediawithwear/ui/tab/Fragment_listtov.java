package com.example.mediawithwear.ui.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediawithwear.AdapterNext;
import com.example.mediawithwear.App;
import com.example.mediawithwear.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fragment_listtov  extends Fragment {

    String group;
    AdapterNext adapter;
    @BindView(R.id.recyc_vp)
    RecyclerView recyc;


    public static Fragment newInstance(String s) {
        Fragment_listtov listtov= new Fragment_listtov();
        listtov.group = s;
        return listtov;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main2,container,false);
        ButterKnife.bind(this,view);
        recyc.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new AdapterNext(App.dm.getContForGroup(group));
        recyc.setAdapter(adapter);

        return view;
    }
}
