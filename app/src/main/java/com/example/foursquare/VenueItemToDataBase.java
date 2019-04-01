package com.example.foursquare;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.foursquare.networking.venuelist.json.Item;

import java.io.Serializable;


public class VenueItemToDataBase implements Serializable {

    private String code;
    private String name;
    private String shortName;
    private String adress;
    private String prefix;
    private String syfix;
    private int distance;
    private double latitude;
    private double longtitude;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSyfix() {
        return syfix;
    }

    public void setSyfix(String syfix) {
        this.syfix = syfix;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public VenueItemToDataBase(String code, String name, String shortName, String adress, String prefix, String syfix, int distance, double latitude, double longtitude) {
        this.code = code;
        this.name = name;
        this.shortName = shortName;
        this.adress = adress;
        this.prefix = prefix;
        this.syfix = syfix;
        this.distance = distance;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public static VenueItemToDataBase from(Item item){
        return new VenueItemToDataBase(
                item.getVenue().getId(),
                item.getVenue().getName(),
                item.getVenue().getCategories().get(0).getShortName(),item.getVenue().getLocation().getAddress(),
                item.getVenue().getCategories().get(0).getIcon().getPrefix(),
                item.getVenue().getCategories().get(0).getIcon().getSuffix(),
                item.getVenue().getLocation().getDistance(),
                item.getVenue().getLocation().getLat(),
                item.getVenue().getLocation().getLng());
    }
}
