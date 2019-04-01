package com.example.foursquare.adapterUtils;

import com.example.foursquare.R;
import com.example.foursquare.VenueItemToDataBase;
import com.example.foursquare.networking.venueitem.jsononeitem.ItemTips;

import java.util.Calendar;

public class Helper {

    private static Calendar getDate(ItemTips tip) {
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(tip.getCreatedAt() * 1000);
        return date;
    }

    public static String getDay(ItemTips tip) {
        String newDay = getDate(tip).get(Calendar.DAY_OF_MONTH) + "";
        return newDay;
    }

    public static String getMoth(ItemTips tip) {
        int dOfMonth = getDate(tip).get(Calendar.MONTH);
        String dayOfMonth = new String();
        if (dOfMonth == 0) {
            dayOfMonth = "January";
        }
        if (dOfMonth == 1) {
            dayOfMonth = "February";
        }
        if (dOfMonth == 2) {
            dayOfMonth = "March";
        }
        if (dOfMonth == 3) {
            dayOfMonth = "April";
        }
        if (dOfMonth == 4) {
            dayOfMonth = "May";
        }
        if (dOfMonth == 5) {
            dayOfMonth = "June";
        }
        if (dOfMonth == 6) {
            dayOfMonth = "July";
        }
        if (dOfMonth == 7) {
            dayOfMonth = "August";
        }
        if (dOfMonth == 8) {
            dayOfMonth = "September";
        }
        if (dOfMonth == 9) {
            dayOfMonth = "October";
        }
        if (dOfMonth == 10) {
            dayOfMonth = "November";
        }
        if (dOfMonth == 11) {
            dayOfMonth = "December";
        }
        return dayOfMonth;
    }

    public static String getYear(ItemTips tip) {
        String newYear = getDate(tip).get(Calendar.YEAR) + "";
        return newYear;
    }

    public static String getHours(ItemTips tip) {
        int hour = getDate(tip).get(Calendar.HOUR_OF_DAY);
        String newHour;
        if (hour < 10) {
            newHour = "0" + hour;
        } else newHour = hour + "";
        return newHour;
    }

    public static String getMinutes(ItemTips tip) {
        int minutes = getDate(tip).get(Calendar.MINUTE);
        String newMinutes;
        if (minutes < 10) {
            newMinutes = "0" + minutes;
        } else newMinutes = minutes + "";
        return newMinutes;
    }

    public static String getLikes(Integer likes) {
        String newLikes;
        if (likes >= 1000) {
            newLikes = likes / 1000 + "K";
        } else newLikes = likes + "";
        return newLikes;
    }

    public static int getRaitingColor(double color){

        int colorId = 0;

        if(Double.valueOf(color)>=0.0&&Double.valueOf(color)<2.0 ){
            // raitingTextView.setBackgroundResource(R.color.raiting2);
            colorId = R.color.raiting2;
        }
        if(Double.valueOf(color)>=2.0&&Double.valueOf(color)<4.0 ){
            //raitingTextView.setBackgroundResource(R.color.raiting4);
            colorId = R.color.raiting4;
        }
        if(Double.valueOf(color)>=4.0&&Double.valueOf(color)<6.0 ){
            colorId = R.color.raiting6;
            //raitingTextView.setBackgroundResource(R.color.raiting6);
        }
        if(Double.valueOf(color)>=6.0&&Double.valueOf(color)<8.0 ){
            colorId = R.color.raiting8;
            // raitingTextView.setBackgroundResource(R.color.raiting8);
        }
        if(Double.valueOf(color)>=8.0&&Double.valueOf(color)<9.0 ){
            colorId = R.color.raiting9;
            //  raitingTextView.setBackgroundResource(R.color.raiting9);
        }
        if(Double.valueOf(color)>=9.0){
            colorId = R.color.raiting10;
            //  raitingTextView.setBackgroundResource(R.color.raiting10);
        }
        return colorId;
    }

    public static String getAdressWithDistance(VenueItemToDataBase venueItemToDataBase){
        int dis = venueItemToDataBase.getDistance();
        String distance;
        if(dis<1000){
            distance = "0."+dis+" km, ";
        }
        else distance = Integer.toString(venueItemToDataBase.getDistance()).substring(0, 1)+"." +
                Integer.toString(venueItemToDataBase.getDistance()).substring(1)+" km, ";
        distance = distance + venueItemToDataBase.getAdress();

        return distance;
    }

}
