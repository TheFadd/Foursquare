package com.example.foursquare.ui.detail.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foursquare.R;
import com.example.foursquare.data.networking.venueitem.models.User;
import com.example.foursquare.ui.GeneralUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.BaseHolder> {

    private static final int HEADER_ITEM = 0;
    private static final int TIP_ITEM = 1;

    private final List<VenueRecyclerCell> items = new ArrayList<>();
    private final Context context;
    private final double latitude;
    private final double longitude;

    public DetailAdapter(Context context, double latitude, double longitude) {
        this.context = context;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void swapData(List<VenueRecyclerCell> data) {
        items.clear();
        items.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == HEADER_ITEM) {
            return new HeaderHolder(inflater.inflate(R.layout.adapter_header, parent, false));
        } else if (viewType == TIP_ITEM) {
            return new TipItemHolder(inflater.inflate(R.layout.comment_item_with_photo, parent, false));
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        VenueRecyclerCell recyclerViewItem = items.get(position);
        if (recyclerViewItem instanceof HeaderCell) {
            return HEADER_ITEM;
        } else if (recyclerViewItem instanceof TiperCell) {
            return TIP_ITEM;
        } else {
            return super.getItemViewType(position);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private class TipItemHolder extends BaseHolder {
        private final TextView authorNameTextView;
        private final TextView countOfLikesTextView;
        private final TextView commentTextView;
        private final TextView commentCreatedTextView;
        private final ImageView commentatorImageView;
        private final ImageView photoMadeByUserImageView;

        TipItemHolder(View itemView) {
            super(itemView);
            authorNameTextView = itemView.findViewById(R.id.name_of_commentator);
            countOfLikesTextView = itemView.findViewById(R.id.count_of_likes);
            commentTextView = itemView.findViewById(R.id.comment);
            commentCreatedTextView = itemView.findViewById(R.id.comment_created_time);
            commentatorImageView = itemView.findViewById(R.id.commentator_icon);
            photoMadeByUserImageView = itemView.findViewById(R.id.photo_made_by_user);
        }

        @Override
        void bind(VenueRecyclerCell cell) {
            TiperCell tipItem = (TiperCell) cell;
            String day = GeneralUtil.getDay(tipItem.getItemTips());
            String month = GeneralUtil.getMonth(tipItem.getItemTips());
            String year = GeneralUtil.getYear(tipItem.getItemTips());
            String hour = GeneralUtil.getHours(tipItem.getItemTips());
            String minutes = GeneralUtil.getMinutes(tipItem.getItemTips());
            String dateCreated = day + " " + month + " " + year + "   " + hour + ":" + minutes;

            User user = tipItem.getItemTips().getUser();
            authorNameTextView.setText(user.getFirstName() + " " + user.getLastName());
            countOfLikesTextView.setText(GeneralUtil.getLikes(tipItem.getItemTips().getLikes().getCount()));
            if (!tipItem.getItemTips().getText().isEmpty()) {
                commentTextView.setText(tipItem.getItemTips().getText());
            }
            commentCreatedTextView.setText(dateCreated);
            if (tipItem.getItemTips().getBestPhoto() != null) {
                String url = tipItem.getItemTips().getBestPhoto().getPrefix() + tipItem.getItemTips().getBestPhoto().getWidth()
                        + "x" + tipItem.getItemTips().getBestPhoto().getHeight() + tipItem.getItemTips().getBestPhoto().getSuffix();
                Glide.with(context).load(url).into(photoMadeByUserImageView);
            }
            String url = user.getUserPhoto().getPrefix() + user.getId() + user.getUserPhoto().getSuffix();
            Glide.with(context).load(url).into(commentatorImageView);
        }
    }

    private class HeaderHolder extends BaseHolder implements OnMapReadyCallback {

        private final ImageView imageViewOne;
        private final ImageView imageViewTwo;
        private final ImageView imageViewThree;
        private final TextView nameOfVenueTextView;
        private final TextView ratingTextView;
        private final TextView shortNameTextView;
        private final TextView descriptionOneTextView;
        private final TextView priceTextView;
        private final TextView addressTextView;
        private final MapView map;
        private final TextView tipName;

        HeaderHolder(View itemView) {
            super(itemView);
            map = itemView.findViewById(R.id.map);
            map.onCreate(null);
            map.onResume();
            map.getMapAsync(this);
            tipName = itemView.findViewById(R.id.tips);
            nameOfVenueTextView = itemView.findViewById(R.id.name_of_venue);
            ratingTextView = itemView.findViewById(R.id.rating);
            shortNameTextView = itemView.findViewById(R.id.short_name);
            descriptionOneTextView = itemView.findViewById(R.id.description_1);
            priceTextView = itemView.findViewById(R.id.price);
            addressTextView = itemView.findViewById(R.id.address);
            imageViewOne = itemView.findViewById(R.id.photo_1);
            imageViewTwo = itemView.findViewById(R.id.photo_2);
            imageViewThree = itemView.findViewById(R.id.photo_3);
        }

        @Override
        void bind(VenueRecyclerCell cell) {
            HeaderCell header = (HeaderCell) cell;
            tipName.setText("Tips");

            int colorId = GeneralUtil.getRatingColor(Double.valueOf(header.getDetailInformation().getRating()));
            ratingTextView.setBackgroundResource(colorId);
            if (!header.getDetailInformation().getVenueName().isEmpty()) {
                nameOfVenueTextView.setText(header.getDetailInformation().getVenueName());
            }
            if (!header.getDetailInformation().getAddressWithDistance().isEmpty()) {
                addressTextView.setText(header.getDetailInformation().getAddressWithDistance());
            }
            if (!header.getDetailInformation().getDescriptions().isEmpty()) {
                descriptionOneTextView.setText(header.getDetailInformation().getDescriptions());
            }
            if (!header.getDetailInformation().getPrice().isEmpty()) {
                priceTextView.setText(header.getDetailInformation().getPrice());
            }
            if (!header.getDetailInformation().getRating().isEmpty()) {
                if (Double.valueOf(header.getDetailInformation().getRating()) < 0.0) {
                    header.getDetailInformation().setRating("");
                } else {
                    ratingTextView.setText(header.getDetailInformation().getRating());
                }
            }
            if (!header.getDetailInformation().getShortName().isEmpty()) {
                shortNameTextView.setText(header.getDetailInformation().getShortName());
            }
            if (!header.getDetailInformation().getBestPhoto().isEmpty()) {
                Glide.with(context).load(header.getDetailInformation().getBestPhoto()).into(imageViewTwo);
            }
            if (!header.getDetailInformation().getIcon().isEmpty()) {
                Glide.with(context).load(header.getDetailInformation().getIcon()).into(imageViewOne);
            }
            Glide.with(context).load(R.drawable.ic_blue_cat).into(imageViewThree);
        }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng location = new LatLng(latitude, longitude);
            googleMap.addMarker(new MarkerOptions().position(location).draggable(true));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16f));
            googleMap.getUiSettings().setScrollGesturesEnabled(false);
            googleMap.getUiSettings().setZoomGesturesEnabled(false);
            googleMap.getUiSettings().setAllGesturesEnabled(false);
        }
    }


    static abstract class BaseHolder extends RecyclerView.ViewHolder {

        BaseHolder(@NonNull View itemView) {
            super(itemView);
        }

        abstract void bind(VenueRecyclerCell cell);
    }

}


