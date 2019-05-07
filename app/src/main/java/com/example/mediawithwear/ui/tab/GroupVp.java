package com.example.mediawithwear.ui.tab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class GroupVp extends FragmentPagerAdapter {

    private List<String> groups;

    public GroupVp(FragmentManager fm, List<String> groups) {
        super(fm);
        this.groups=groups;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return Fragment_listtov.newInstance (groups.get(position));
    }


    @Override
    public int getCount() {
        return groups.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return groups.get(position);
    }
}
