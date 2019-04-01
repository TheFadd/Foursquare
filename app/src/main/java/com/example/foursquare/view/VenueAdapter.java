package com.example.foursquare.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foursquare.R;
import com.example.foursquare.VenueItemToDataBase;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VenueAdapter  extends RecyclerView.Adapter<VenueAdapter.VenueViewHolder>{


    public interface VenueListener {
        void onVenue(VenueItemToDataBase item);
    }

    private List<VenueItemToDataBase> items;
    private Context context;
    private VenueListener listener;

    public VenueAdapter( List<VenueItemToDataBase> venues,Context ctext,VenueListener listener) {
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
        final VenueItemToDataBase item = items.get(position);

          String name = item.getName();
          int dis = item.getDistance();
          String distance;
          if(dis<1000){
              distance = "0."+dis+" km, ";
          }
          else distance = Integer.toString(item.getDistance()).substring(0, 1)+"." +
                Integer.toString(item.getDistance()).substring(1)+" km, ";

         distance = distance + item.getAdress();


          String shortName = item.getShortName();
          String icon  = item.getPrefix() +"bg_64"+
                         item.getSyfix();

          holder.nameOfVenueView.setText(name);
          holder.distanceView.setText(distance);
          holder.shortNameView.setText(shortName);
         // holder.adressView.setText(adress);

        Picasso.with(context).load(icon).into(holder.imageView);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onVenue(item);
                notifyDataSetChanged();
            }
        });

//        holder.maxTempView.setText(Integer.toString(tMax)+"°");
//        holder.minTempView.setText(Integer.toString(tMin)+"°");
//        holder.descriptionView.setText(item.getDescription());
//        holder.dayOfWeekView.setText(dayOfWeek + dom +" " + dayOfMonth);
//        Picasso.with(context).load(item.getIcon()).into(holder.imageView);
//
//        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listener.onWeatherDay(item);
//                notifyDataSetChanged();
//            }
//        });
    }

    public void swapData(List<VenueItemToDataBase> data){
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
