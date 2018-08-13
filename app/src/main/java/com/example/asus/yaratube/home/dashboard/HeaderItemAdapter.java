package com.example.asus.yaratube.home.dashboard;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.asus.yaratube.R;
import com.example.asus.yaratube.data.model.Headeritem;

import java.util.List;

import static com.example.asus.yaratube.util.Util.BASE_URL;

public class HeaderItemAdapter extends RecyclerView.Adapter<HeaderItemAdapter.HeaderItemViewHolder> {

    private List<Headeritem> headeritems;

    HeaderItemAdapter() {

    }

    public void setHeaderItems(List<Headeritem> headeritems) {
        this.headeritems = headeritems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HeaderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View result = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_headeritem, parent, false);
        return new HeaderItemViewHolder(result);
    }

    @Override
    public void onBindViewHolder(@NonNull HeaderItemViewHolder holder, int position) {

        holder.onBind(headeritems.get(position));
    }

    @Override
    public int getItemCount() {
        if(headeritems == null)
            return 0;
        return headeritems.size();
    }

    class HeaderItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView headerImage;

        HeaderItemViewHolder(View itemView) {
            super(itemView);

            headerImage = itemView.findViewById(R.id.header_image);
        }

        void onBind(Headeritem headeritem) {

            if(headeritem.getFeatureAvatar() != null) {
                Glide.with(itemView.getContext()).load(headeritem.getFeatureAvatarUrl()).into(headerImage);
            }
        }
    }
}
