package com.example.foursquare.ui;

import android.annotation.SuppressLint;

import com.example.foursquare.R;
import com.example.foursquare.ui.list.VenueModel;
import com.example.foursquare.data.networking.venueitem.models.BestPhoto;
import com.example.foursquare.data.networking.venueitem.models.ItemTips;
import com.example.foursquare.data.networking.venueitem.models.Price;
import com.example.foursquare.data.networking.venueitem.models.ResponseItem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GeneralUtil {

    private static Calendar getDate(ItemTips tip) {
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(tip.getCreatedAt() * 1000);
        return date;
    }

    public static String getDay(ItemTips tip) {
        return getDate(tip).get(Calendar.DAY_OF_MONTH) + "";
    }

    @SuppressLint("SimpleDateFormat")
    public static String getMonth(ItemTips tip) {
        Date date = getDate(tip).getTime();
        return new SimpleDateFormat("MMMM").format(date);
    }

    public static String getYear(ItemTips tip) {
        return getDate(tip).get(Calendar.YEAR) + "";
    }

    public static String getHours(ItemTips tip) {
        int hour = getDate(tip).get(Calendar.HOUR_OF_DAY);
        String newHour;
        if (hour < 10) {
            newHour = "0" + hour;
        } else {
            newHour = hour + "";
        }
        return newHour;
    }

    public static String getMinutes(ItemTips tip) {
        int minutes = getDate(tip).get(Calendar.MINUTE);
        String newMinutes;
        if (minutes < 10) {
            newMinutes = "0" + minutes;
        } else {
            newMinutes = minutes + "";
        }
        return newMinutes;
    }

    public static String getLikes(Integer likes) {
        String newLikes;
        if (likes >= 1000) {
            newLikes = likes / 1000 + "K";
        } else if (likes >= 1000000) {
            newLikes = likes / 1000000 + "M";
        } else {
            newLikes = likes + "";
        }
        return newLikes;
    }

    public static int getRatingColor(double color) {
        if (color < 0) {
            return 0;
        } else if (color < 2.0) {
            return R.color.raiting2;
        } else if (color < 4.0) {
            return R.color.raiting4;
        } else if (color < 6.0) {
            return R.color.raiting6;
        } else if (color < 8.0) {
            return R.color.raiting8;
        } else if (color < 9.0) {
            return R.color.raiting9;
        } else {
            return R.color.raiting10;
        }
    }

    public static String getAddressWithDistance(VenueModel venueItemToDataBase) {
        double dis = venueItemToDataBase.getDistance();
        int di = venueItemToDataBase.getDistance();
        String address = venueItemToDataBase.getAddress();
        String km = " km, ";
        if (address == null) {
            address = "";
            km = " km";
        }
        String distance = "";
        if (dis < 1000) {
            distance = "0." + di + km;
        } else {
            distance = dis / 1000 + km;
        }
        distance = distance + address;
        return distance;
    }

    public static ResponseItem checkItem(ResponseItem item) {
        ResponseItem local = item;
        if (item.getVenueItem().getRating() == null) {
            local.getVenueItem().setRating(-1.0);
        }
        if (item.getVenueItem().getPrice() == null) {
            Price price = new Price("");
            local.getVenueItem().setPrice(price);
        }
        if (item.getVenueItem().getBestPhoto() == null) {
            item.getVenueItem().setBestPhoto(new BestPhoto("", "", "", "", ""));
        }
        List<ItemTips> list = item.getVenueItem().getTips().getGroups().get(0).getItemTips();

        for (ItemTips itemTips:list){
            if (itemTips.getUser().getLastName() == null) {
                itemTips.getUser().setLastName("");
            }
            if (itemTips.getUser().getFirstName() == null) {
                itemTips.getUser().setFirstName("");
            }
        }
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).getUser().getLastName() == null) {
//                list.get(i).getUser().setLastName("");
//            }
//            if (list.get(i).getUser().getFirstName() == null) {
//                list.get(i).getUser().setFirstName("");
//            }
//        }
        return local;
    }
}
