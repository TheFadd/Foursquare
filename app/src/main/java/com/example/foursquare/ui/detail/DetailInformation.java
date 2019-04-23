package com.example.foursquare.ui.detail;

import com.example.foursquare.data.networking.venueitem.models.ItemTips;
import com.example.foursquare.data.networking.venueitem.models.ResponseItem;

import java.util.List;

public class DetailInformation {

    private String id;
    private String venueName;
    private String rating;
    private String shortName;
    private String price;
    private String descriptions;
    private String addressWithDistance;
    private String icon;
    private String bestPhoto;
    private double latitude;
    private double longitude;
    private List<ItemTips> tips;

    public List<ItemTips> getTips() {
        return tips;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getId() {
        return id;
    }

    public String getVenueName() {
        return venueName;
    }

    public String getRating() {
        return rating;
    }

    public String getShortName() {
        return shortName;
    }

    public String getPrice() {
        return price;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public String getAddressWithDistance() {
        return addressWithDistance;
    }

    public String getIcon() {
        return icon;
    }

    public String getBestPhoto() {
        return bestPhoto;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public void setAddressWithDistance(String addressWithDistance) {
        this.addressWithDistance = addressWithDistance;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setBestPhoto(String bestPhoto) {
        this.bestPhoto = bestPhoto;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setTips(List<ItemTips> tips) {
        this.tips = tips;
    }

    public DetailInformation(String id, String venueName, String rating, String shortName, String price, String descriptions,
                             String addressWithDistance, String icon, String bestPhoto, double latitude, double longitude, List<ItemTips> tips) {
        this.id = id;
        this.venueName = venueName;
        this.rating = rating;
        this.shortName = shortName;
        this.price = price;
        this.descriptions = descriptions;
        this.addressWithDistance = addressWithDistance;
        this.icon = icon;
        this.bestPhoto = bestPhoto;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tips = tips;
    }

    public static DetailInformation from(TotalInformation info, ResponseItem item, String description, List<ItemTips> tips, String photo) {
        return new DetailInformation(
                info.getId(),
                info.getName(),
                item.getVenueItem().getRating().toString(),
                info.getShortName(),
                item.getVenueItem().getPrice().getCurrency(),
                description,
                info.getAddressWithDistance(),
                info.getPrefix() + "bg_64" + info.getSuffix(),
                photo,
                info.getLatitude(),
                info.getLongitude(),
                tips);
    }
}
