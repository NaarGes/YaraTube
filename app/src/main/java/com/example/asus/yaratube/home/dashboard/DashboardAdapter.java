package com.example.asus.yaratube.home.dashboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.yaratube.R;
import com.example.asus.yaratube.data.model.Headeritem;
import com.example.asus.yaratube.data.model.Homeitem;
import com.example.asus.yaratube.data.model.Store;

import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder> {

    private Store store;
    private List<Homeitem> homeitems;
    private List<Headeritem> headeritems;
    private boolean isHome = false;
    private Context context;

    DashboardAdapter(Context context) {

        this.context = context;
    }

    public void setStore(Store store) {
        this.store = store;
        this.homeitems = store.getHomeitem();
        this.headeritems = store.getHeaderitem();
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == 0) { // header list
            isHome = false;
            View result = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dashboard_header, parent, false);
            return new DashboardViewHolder(result);
        } else if(viewType == 1) {
            isHome = true;
            View result = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dashboard_home, parent, false);
            return new DashboardViewHolder(result);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardViewHolder holder, int position) {

        if(isHome)
            holder.onBindHomeList(homeitems.get(position));
        else
            holder.onBindHeaderList();
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

    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);

        if(position == 0)
            return 0; // header list
        return 1; // home list
    }

    class DashboardViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView homeRecyclerView;
        private TextView productListName;
        private RecyclerView headerRecyclerView;
        private RecyclerView.ItemDecoration itemDecoration;

        DashboardViewHolder(View itemView) {
            super(itemView);

            homeRecyclerView = itemView.findViewById(R.id.homeitem_recycler_view);
            productListName = itemView.findViewById(R.id.product_list_name);
            headerRecyclerView = itemView.findViewById(R.id.headeritem_recycler_view);

            itemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);

        }

        void onBindHomeList(Homeitem homeitem) {

            homeRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            homeRecyclerView.addItemDecoration(itemDecoration);

            HomeItemAdapter adapter = new HomeItemAdapter();
            adapter.setProducts(homeitem.getProducts());
            homeRecyclerView.setAdapter(adapter);

            productListName.setText(homeitem.getTitle());
        }

        void onBindHeaderList() {

            headerRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            headerRecyclerView.addItemDecoration(itemDecoration);

            HeaderItemAdapter adapter = new HeaderItemAdapter();
            adapter.setHeaderItems(headeritems);
            headerRecyclerView.setAdapter(adapter);
        }

    }
}
