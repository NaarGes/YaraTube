package com.example.asus.yaratube.home.dashboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.yaratube.R;
import com.example.asus.yaratube.data.model.Headeritem;
import com.example.asus.yaratube.data.model.Homeitem;
import com.example.asus.yaratube.data.model.Store;

import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Homeitem> homeitems;
    private List<Headeritem> headeritems;
    private Context context;

    private HeaderAdapter headerAdapter;
    private FragmentManager fragmentManager;
    private ViewPager viewPager;


    private static int HEADER_VIEW = 1;
    private static int HOME_VIEW = 2;

    DashboardAdapter(Context context, FragmentManager fragmentManager) {

        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    public void setStore(Store store) {
        this.headeritems = store.getHeaderitem();
        this.homeitems = store.getHomeitem();
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == HOME_VIEW) {
            View result = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dashboard_home, parent, false);
            return new HomeViewHolder(result);

        } else if(viewType == HEADER_VIEW) {

            View result = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dashboard_header, parent, false);
            return new HeaderViewHolder(result);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        int viewType = holder.getItemViewType();

        if(viewType == HOME_VIEW)
            ((HomeViewHolder) holder).onBind(homeitems.get(position - 1));

        else if (viewType == HEADER_VIEW)
            ((HeaderViewHolder) holder).onBind();

    }

    @Override
    public int getItemViewType(int position) {

        if(position == 0)
            return HEADER_VIEW;
        return HOME_VIEW;
    }

    @Override
    public int getItemCount() {

        if(headeritems == null && homeitems == null)
            return 0;
        else if(headeritems == null)
            return homeitems.size();
        else if(homeitems == null)
            return 1;
        return 1 + homeitems.size();
    }


    class HomeViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView homeRecyclerView;
        private TextView productListName;

        HomeViewHolder(View itemView) {
            super(itemView);

            homeRecyclerView = itemView.findViewById(R.id.homeitem_recycler_view);
            productListName = itemView.findViewById(R.id.product_list_name);
        }

        void onBind(Homeitem homeitem) {

            homeRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

            HomeItemAdapter adapter = new HomeItemAdapter();
            adapter.setProducts(homeitem.getProducts());
            homeRecyclerView.setAdapter(adapter);

            productListName.setText(homeitem.getTitle());
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        HeaderViewHolder(View itemView) {
            super(itemView);

            viewPager = itemView.findViewById(R.id.header_view_pager);
            Log.d("header view holder ", "view pager ->" + viewPager);
        }

        void onBind() {

            headerAdapter = new HeaderAdapter(fragmentManager);
            viewPager.setAdapter(headerAdapter);
            Log.d("header view holder ", "view adapter ->" + viewPager);
        }
    }

    public class HeaderAdapter extends FragmentPagerAdapter {

        HeaderAdapter(FragmentManager fm) {
            super(fm);
            Log.d("headeradapter fm ", ""+fm);
        }

        @Override
        public Fragment getItem(int position) {

            Log.d("getitem position", ""+position);
            return HeaderFragment.newInstance(headeritems.get(position));
        }

        @Override
        public int getCount() {

            if (headeritems == null)
                return 0;
            return headeritems.size();
        }
    }
}
