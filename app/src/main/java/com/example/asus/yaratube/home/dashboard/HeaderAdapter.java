package com.example.asus.yaratube.home.dashboard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.asus.yaratube.data.model.Product;

import java.util.List;


public class HeaderAdapter extends FragmentStatePagerAdapter {

    private List<Product> headeritems;

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

    public void setHeaderitems(List<Product> headeritems) {
        this.headeritems = headeritems;
        notifyDataSetChanged();
    }
}

