package com.example.foursquare.ui.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foursquare.R;
import com.example.foursquare.ui.GeneralUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VenueAdapter extends RecyclerView.Adapter<VenueAdapter.VenueViewHolder> {

            public interface VenueListener {
                void onVenue(VenueModel item);
            }

            private final List<VenueModel> items = new ArrayList<>();
            private final Context context;
            private final VenueListener listener;

    public VenueAdapter(Context context, VenueListener listener) {
                this.context = context;
                this.listener = listener;
            }

            @NonNull
            @Override
            public VenueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.venue_list_item, parent, false);
                return new VenueViewHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull final VenueViewHolder holder, final int position) {
                final VenueModel item = items.get(position);
                String name = item.getName();
                String distance = GeneralUtil.getAddressWithDistance(item);
                String shortName = item.getShortName();
                String icon = item.getPrefix() + "bg_64" + item.getSuffix();
                holder.nameOfVenueView.setText(name);
                holder.distanceView.setText(distance);
                holder.shortNameView.setText(shortName);
                Picasso.with(context).load(icon).into(holder.imageView);
                holder.itemView.setOnClickListener(view -> listener.onVenue(item));
            }

            public void swapData(List<VenueModel> data) {
                items.clear();
                items.addAll(data);
                notifyDataSetChanged();
            }

            @Override
            public int getItemCount() {
                return items.size();
            }

            public static class VenueViewHolder extends RecyclerView.ViewHolder {

                @BindView(R.id.short_name)
                TextView shortNameView;

                @BindView(R.id.distance)
                TextView distanceView;

                @BindView(R.id.name_of_venue)
                TextView nameOfVenueView;

        @BindView(R.id.venue_icon)
        ImageView imageView;

        VenueViewHolder(View viewItem) {
            super(viewItem);
            ButterKnife.bind(this, viewItem);
        }
    }
}
