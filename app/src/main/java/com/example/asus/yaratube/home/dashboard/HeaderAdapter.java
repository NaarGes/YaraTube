package com.example.asus.yaratube.home.dashboard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.example.asus.yaratube.data.model.Headeritem;

import java.util.List;


public class HeaderAdapter extends FragmentStatePagerAdapter {

    private List<Headeritem> headeritems;
    private DashboardContract.onHomeItemClickListener listener;

    HeaderAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return HeaderFragment.newInstance(headeritems.get(position), listener);
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

    public void setListener(DashboardContract.onHomeItemClickListener listener) {
        this.listener = listener;
    }
}

