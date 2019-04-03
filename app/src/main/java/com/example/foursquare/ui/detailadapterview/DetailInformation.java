package com.example.foursquare.ui.detailadapterview;

import com.example.foursquare.data.networking.venueitem.jsononeitem.ItemTips;
import com.example.foursquare.data.networking.venueitem.jsononeitem.ResponseItem;

import java.util.List;

public class DetailInformation {

    private String id;
    private String venueName;
    private String raiting;
    private String shortName;
    private String price;
    private String discriptions;
    private String adressWithDistanse;
    private String icon;
    private String bestPhoto;
    private String latitude;
    private String longtitude;
    private List<ItemTips> tips;

    public List<ItemTips> getTips() {
        return tips;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public String getId() {
        return id;
    }

    public String getVenueName() {
        return venueName;
    }

    public String getRaiting() {
        return raiting;
    }

    public String getShortName() {
        return shortName;
    }

    public String getPrice() {
        return price;
    }

    public String getDiscriptions() {
        return discriptions;
    }

    public String getAdressWithDistanse() {
        return adressWithDistanse;
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

    public void setRaiting(String raiting) {
        this.raiting = raiting;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDiscriptions(String discriptions) {
        this.discriptions = discriptions;
    }

    public void setAdressWithDistanse(String adressWithDistanse) {
        this.adressWithDistanse = adressWithDistanse;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setBestPhoto(String bestPhoto) {
        this.bestPhoto = bestPhoto;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public void setTips(List<ItemTips> tips) {
        this.tips = tips;
    }

    public DetailInformation(String id, String venueName, String raiting, String shortName, String price, String discriptions,
                             String adressWithDistanse, String icon, String bestPhoto, String latitude, String longtitude, List<ItemTips> tips) {
        this.id = id;
        this.venueName = venueName;
        this.raiting = raiting;
        this.shortName = shortName;
        this.price = price;
        this.discriptions = discriptions;
        this.adressWithDistanse = adressWithDistanse;
        this.icon = icon;
        this.bestPhoto = bestPhoto;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.tips = tips;
    }

    public static DetailInformation from(InfoFromItemVenueList info, ResponseItem item, String discription, List<ItemTips> tips, String photo){
        return new DetailInformation(
                info.getId(),
                info.getName(),
                item.getVenueItem().getRating().toString(),
                info.getShorName(),
                item.getVenueItem().getPrice().getCurrency(),
                discription,
                info.getAdressWithDistance(),
                info.getPrefix()+"bg_64"+info.getSyfix(),
                photo,
                info.getLatitude(),
                info.getLongtitude(),
                tips);
    }
}
