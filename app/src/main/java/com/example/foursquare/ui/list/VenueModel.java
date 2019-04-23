package com.example.foursquare.ui.list;

import com.example.foursquare.data.networking.venuelist.models.Category;
import com.example.foursquare.data.networking.venuelist.models.Item;
import com.example.foursquare.data.networking.venuelist.models.Venue;

import java.io.Serializable;


public class VenueModel implements Serializable {

    private String code;
    private String name;
    private String shortName;
    private String address;
    private String prefix;
    private String suffix;
    private int distance;
    private double latitude;
    private double longitude;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public VenueModel(String code, String name, String shortName, String address, String prefix, String suffix, int distance, double latitude, double longitude) {
        this.code = code;
        this.name = name;
        this.shortName = shortName;
        this.address = address;
        this.prefix = prefix;
        this.suffix = suffix;
        this.distance = distance;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static VenueModel from(Item item) {
        Venue venue = item.getVenue();
        Category category = venue.getCategories().get(0);
        return new VenueModel(
                venue.getId(),
                venue.getName(),
                category.getShortName(),
                venue.getLocation().getAddress(),
                category.getIcon().getPrefix(),
                category.getIcon().getSuffix(),
                venue.getLocation().getDistance(),
                venue.getLocation().getLat(),
                venue.getLocation().getLng());
    }
}
