package com.example.mediawithwear.ui.tab;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class GroupVP extends FragmentPagerAdapter {

    List<String> gr;

    public GroupVP(FragmentManager fm, List<String> groups) {
        super(fm);
        gr = groups;
    }

    @Override
    public int getCount() {
        return gr.size();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return Fragment_list.newInstance(gr.get(position));
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return gr.get(position);
    }
}
