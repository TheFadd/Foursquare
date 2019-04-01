package com.example.foursquare.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.foursquare.DetailInformation;
import com.example.foursquare.R;
import com.example.foursquare.adapterUtils.Helper;
import com.example.foursquare.networking.venueitem.jsononeitem.ItemTips;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.TipsViewHolder> {

    private List<ItemTips> tips;
    private Context context;
    private DetailInformation detailInformation;

//    private ImageView imageViewOne;
//    private ImageView imageViewTwo;
//    private TextView nameOfVenueTextView;
//    private TextView raitingTextView;
//    private TextView shortNameTextView;
//    private TextView discriptionOneTextView;
//    private TextView priceTextView;
//    private TextView adressTextView;

    public TipsAdapter( List<ItemTips> tipsitem,Context ctext) {
        tips = tipsitem;
        context = ctext;

    }

    @Override
    public TipsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item_with_photo, parent, false);
        return new TipsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TipsViewHolder holder, final int position) {
        final ItemTips tip = tips.get(position);
////
//        if (position ==1){
//            setView(detailInformation);
//
//        }
        if(tip.getBestPhoto()!=null){
            String prefix = tip.getBestPhoto().getPrefix();
            String heighWidth = tip.getBestPhoto().getHeight()+"x"+tip.getBestPhoto().getWidth();
            String syfix = tip.getBestPhoto().getSuffix();
            String photoMadeByUser = prefix+heighWidth+syfix;
            Picasso.with(context).load(photoMadeByUser).into(holder.photoMadeByUserImageView);
        }
        String comment = tip.getText();
        String likes = Helper.getLikes(tip.getLikes().getCount());
        String day = Helper.getDay(tip);
        String month = Helper.getMoth(tip);
        String year = Helper.getYear(tip);
        String hour = Helper.getHours(tip);
        String minutes = Helper.getMinutes(tip);

        String dateCreated = day+" "+month+" "+year+"   "+hour+":"+minutes;
        String nameOfAutor = tip.getUser().getFirstName()+" "+tip.getUser().getLastName();
        String photoAutor = tip.getUser().getUserPhoto().getPrefix()+tip.getUser().getId()+tip.getUser().getUserPhoto().getSuffix();

        Picasso.with(context).load(photoAutor).into(holder.commentatorImageView);
                holder.authorNameTextView.setText(nameOfAutor);
                holder.commentTextView.setText(comment);
                holder.countOfLikesTextView.setText(likes);
                holder.commentCreatedTextView.setText(dateCreated);
    }

    @Override
    public int getItemCount() {
        return tips.size();
    }

    public static class TipsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name_of_commentator)
        TextView authorNameTextView;

        @BindView(R.id.count_of_likes)
        TextView countOfLikesTextView;

        @BindView(R.id.comment)
        TextView commentTextView;

        @BindView(R.id.comment_created_time)
        TextView commentCreatedTextView;

        @BindView(R.id.commentator_icon)
        ImageView commentatorImageView;

        @BindView(R.id.photo_made_by_user)
        ImageView photoMadeByUserImageView;

//        @BindView(R.id.test_up)
//        ConstraintLayout linearLayout;
//
//
//
//        @BindView(R.id.name_of_venue)
//        TextView nameOfVenueTextView ;
//        @BindView(R.id.raiting)
//        TextView raitingTextView;
//        @BindView(R.id.short_name)
//        TextView shortNameTextView;
//        @BindView(R.id.discription_1)
//        TextView discriptionOneTextView;
//        @BindView(R.id.price)
//        TextView priceTextView ;
//        @BindView(R.id.adress)
//        TextView adressTextView;
//        @BindView(R.id.photo_1)
//        ImageView imageViewOne;
//        @BindView(R.id.photo_2)
//        ImageView imageViewTwo;

        TipsViewHolder(View viewItem) {
            super(viewItem);
            ButterKnife.bind(this, viewItem);
        }
    }

//    private  void setView(DetailInformation detailInformation){
//
//        if(detailInformation.getVenueName() != null){
//            nameOfVenueTextView.setText(detailInformation.getVenueName());
//        }
//
//        if(detailInformation.getShortName()!= null){
//            shortNameTextView.setText(detailInformation.getShortName());
//        }
//
//        if (detailInformation.getRaiting()!= null){
//            raitingTextView.setText(detailInformation.getRaiting());
//        }
//
//        if(detailInformation.getDiscriptions()!= null){
//            discriptionOneTextView.setText(detailInformation.getDiscriptions());
//        }
//        if(detailInformation.getPrice()!= null){
//            priceTextView.setText(detailInformation.getPrice());
//        }
//
//        if(detailInformation.getAdressWithDistanse()!= null){
//            adressTextView.setText(detailInformation.getAdressWithDistanse());
//        }
//        if(detailInformation.getIcon()!= null){
//            Picasso.with(context).load(detailInformation.getIcon()).into(imageViewOne);
//        }
//
//        if(detailInformation.getBestPhoto()!= null){
//            Picasso.with(context).load(detailInformation.getBestPhoto()).into(imageViewTwo);
//        }
//    }
}