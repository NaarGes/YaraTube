package com.example.asus.yaratube.home.dashboard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.asus.yaratube.data.model.Headeritem;

import java.util.List;


public class HeaderAdapter extends FragmentPagerAdapter {

    private List<Headeritem> headeritems;
    HeaderAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return HeaderFragment.newInstance(headeritems.get(position));
    }

    @Override
    public int getCount() {
        if (headeritems == null)
            return 0;
        return headeritems.size();
    }

    public void setHeaderitems(List<Headeritem> headeritems) {
        this.headeritems = headeritems;
        notifyDataSetChanged();
    }
}
