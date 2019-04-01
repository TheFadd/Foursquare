package com.example.foursquare.detailAdapterView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foursquare.R;
import com.example.foursquare.adapterUtils.Helper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter {
    private List<RecycleViewItem> recyclerViewItems;
    private static final int HEADER_ITEM = 0;
    private static final int TIP_ITEM = 1;
    private Context context;
    private double latitude;
    private double longtitude;

    public DetailAdapter(List<RecycleViewItem> recyclerViewItems, Context mContext, double latitude, double longtitude) {
        this.recyclerViewItems = recyclerViewItems;
        this.context = mContext;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row;
        if (viewType == HEADER_ITEM) {
            row = inflater.inflate(R.layout.adapter_header, parent, false);
            return new HeaderHolder(row);
        } else if (viewType == TIP_ITEM) {
            row = inflater.inflate(R.layout.comment_item_with_photo, parent, false);
            return new TipItemHolder(row);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecycleViewItem recyclerViewItem = recyclerViewItems.get(position);
        if (holder instanceof HeaderHolder) {
            HeaderHolder headerHolder = (HeaderHolder) holder;
            Header header = (Header) recyclerViewItem;
            headerHolder.tipName.setText("Tips");
            int colorId= Helper.getRaitingColor(Double.valueOf(header.getDetailInformation().getRaiting()));
            headerHolder.nameOfVenueTextView.setText(header.getDetailInformation().getVenueName());
            headerHolder.adressTextView.setText(header.getDetailInformation().getAdressWithDistanse());
            headerHolder.discriptionOneTextView.setText(header.getDetailInformation().getDiscriptions());
            headerHolder.priceTextView.setText(header.getDetailInformation().getPrice());
            headerHolder.raitingTextView.setText(header.getDetailInformation().getRaiting());
            headerHolder.raitingTextView.setBackgroundResource(colorId);
            headerHolder.shortNameTextView.setText(header.getDetailInformation().getShortName());
            Glide.with(context).load(header.getDetailInformation().getBestPhoto()).into(headerHolder.imageViewTwo);
            Glide.with(context).load(R.drawable.ic_blue_cat).into(headerHolder.imageViewThree);
            Glide.with(context).load(header.getDetailInformation().getIcon()).into(headerHolder.imageViewOne);
        } else if (holder instanceof TipItemHolder) {
            TipItemHolder tipItemHolder = (TipItemHolder) holder;
            Tiper tipItem = (Tiper) recyclerViewItem;
            String day = Helper.getDay(tipItem.getItemTips());
            String month = Helper.getMoth(tipItem.getItemTips());
            String year = Helper.getYear(tipItem.getItemTips());
            String hour = Helper.getHours(tipItem.getItemTips());
            String minutes = Helper.getMinutes(tipItem.getItemTips());
            String dateCreated = day+" "+month+" "+year+"   "+hour+":"+minutes;
            tipItemHolder.authorNameTextView.setText(tipItem.getItemTips().getUser().getFirstName() + " " + tipItem.getItemTips().getUser().getLastName());
            tipItemHolder.countOfLikesTextView.setText(Helper.getLikes(tipItem.getItemTips().getLikes().getCount()));
            tipItemHolder.commentTextView.setText(tipItem.getItemTips().getText());
            tipItemHolder.commentCreatedTextView.setText(dateCreated);
            if (tipItem.getItemTips().getBestPhoto() != null) {
                Glide.with(context).load(tipItem.getItemTips().getBestPhoto().getPrefix() + tipItem.getItemTips().getBestPhoto().getWidth()
                        + "x" + tipItem.getItemTips().getBestPhoto().getHeight() + tipItem.getItemTips().getBestPhoto().getSuffix()).into(tipItemHolder.photoMadeByUserImageView);
            }
            Glide.with(context).load(tipItem.getItemTips().getUser().getUserPhoto().getPrefix() + tipItem.getItemTips().getUser().getId() +
                    tipItem.getItemTips().getUser().getUserPhoto().getSuffix()).into(tipItemHolder.commentatorImageView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        RecycleViewItem recyclerViewItem = recyclerViewItems.get(position);
        if (recyclerViewItem instanceof Header)
            return HEADER_ITEM;
        else if (recyclerViewItem instanceof Tiper)
            return TIP_ITEM;
        else
            return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return recyclerViewItems.size();
    }

    private class TipItemHolder extends RecyclerView.ViewHolder {
        private TextView authorNameTextView;
        private TextView countOfLikesTextView;
        private TextView commentTextView;
        private TextView commentCreatedTextView;
        private ImageView commentatorImageView;
        private ImageView photoMadeByUserImageView;

        TipItemHolder(View itemView) {
            super(itemView);
            authorNameTextView = itemView.findViewById(R.id.name_of_commentator);
            countOfLikesTextView = itemView.findViewById(R.id.count_of_likes);
            commentTextView = itemView.findViewById(R.id.comment);
            commentCreatedTextView = itemView.findViewById(R.id.comment_created_time);
            commentatorImageView = itemView.findViewById(R.id.commentator_icon);
            photoMadeByUserImageView = itemView.findViewById(R.id.photo_made_by_user);
        }
    }

    private class HeaderHolder extends RecyclerView.ViewHolder implements OnMapReadyCallback {

        private ImageView imageViewOne;
        private ImageView imageViewTwo;
        private ImageView imageViewThree;
        private TextView nameOfVenueTextView;
        private TextView raitingTextView;
        private TextView shortNameTextView;
        private TextView discriptionOneTextView;
        private TextView priceTextView;
        private TextView adressTextView;
        private TextView authorNameTextView;
        private TextView countOfLikesTextView;
        private TextView commentTextView;
        private TextView commentCreatedTextView;
        private ImageView commentatorImageView;
        private ImageView photoMadeByUserImageView;
        private MapView map;
        private GoogleMap mapCurrent;
        private TextView tipName;

        HeaderHolder(View itemView) {
            super(itemView);

            map = (MapView) itemView.findViewById(R.id.map);
            map.getMapAsync(this::onMapReady);
            if (map != null) {
                map.onCreate(null);
                map.onResume();
                map.getMapAsync(this::onMapReady);
//            }
                tipName = itemView.findViewById(R.id.tips);
                nameOfVenueTextView = itemView.findViewById(R.id.name_of_venue);
                raitingTextView = itemView.findViewById(R.id.raiting);
                shortNameTextView = itemView.findViewById(R.id.short_name);
                discriptionOneTextView = itemView.findViewById(R.id.discription_1);
                priceTextView = itemView.findViewById(R.id.price);
                adressTextView = itemView.findViewById(R.id.adress);
                imageViewOne = itemView.findViewById(R.id.photo_1);
                imageViewTwo = itemView.findViewById(R.id.photo_2);
                imageViewThree = itemView.findViewById(R.id.photo_3);


                authorNameTextView = itemView.findViewById(R.id.name_of_commentator);
                countOfLikesTextView = itemView.findViewById(R.id.count_of_likes);
                commentTextView = itemView.findViewById(R.id.comment);
                commentCreatedTextView = itemView.findViewById(R.id.comment_created_time);
                commentatorImageView = itemView.findViewById(R.id.commentator_icon);
                photoMadeByUserImageView = itemView.findViewById(R.id.photo_made_by_user);
            }
        }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mapCurrent = googleMap;
            LatLng currentLocation = new LatLng(latitude, longtitude);
            googleMap.addMarker(new MarkerOptions().position(currentLocation).draggable(true));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 18f));
            googleMap.getUiSettings().setScrollGesturesEnabled(false);
            googleMap.getUiSettings().setZoomGesturesEnabled(false);
            googleMap.getUiSettings().setAllGesturesEnabled(false);
        }
    }
}


