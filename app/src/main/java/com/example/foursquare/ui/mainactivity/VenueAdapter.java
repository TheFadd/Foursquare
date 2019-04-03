package com.example.foursquare.ui.mainactivity;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foursquare.R;
import com.example.foursquare.ui.adapterutils.Helper;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VenueAdapter extends RecyclerView.Adapter<VenueAdapter.VenueViewHolder> {


    public interface VenueListener {
        void onVenue(VenueItemFromList item);
    }

    private List<VenueItemFromList> items;
    private Context context;
    private VenueListener listener;

    public VenueAdapter(List<VenueItemFromList> venues, Context ctext, VenueListener listener) {
        items = venues;
        context = ctext;
        this.listener = listener;
    }

    @Override
    public VenueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_list_item, parent, false);
        return new VenueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final VenueViewHolder holder, final int position) {
        final VenueItemFromList item = items.get(position);

        String name = item.getName();
        String distance = Helper.getAdressWithDistance(item);
        String shortName = item.getShortName();
        String icon = item.getPrefix() + "bg_64" + item.getSyfix();
        holder.nameOfVenueView.setText(name);
        holder.distanceView.setText(distance);
        holder.shortNameView.setText(shortName);
        Picasso.with(context).load(icon).into(holder.imageView);
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onVenue(item);
                notifyDataSetChanged();
            }
        });
    }

    public void swapData(List<VenueItemFromList> data) {
        items = data;
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

        @BindView(R.id.id_for_listener)
        ConstraintLayout constraintLayout;

        VenueViewHolder(View viewItem) {
            super(viewItem);
            ButterKnife.bind(this, viewItem);
        }
    }
}
