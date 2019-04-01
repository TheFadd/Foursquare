package com.example.foursquare;

import com.example.foursquare.networking.venueitem.jsononeitem.AttributesGroup;
import com.example.foursquare.networking.venueitem.jsononeitem.ItemTips;
import com.example.foursquare.networking.venueitem.jsononeitem.ResponseItem;
import com.example.foursquare.networking.venuelist.json.Item;

import java.util.ArrayList;
import java.util.List;

public class DetailInformation {

    String id;
    String venueName;
    String raiting;
    String shortName;
    String price;
    String discriptions;
    String adressWithDistanse;
    String icon;
    String bestPhoto;
    String latitude;
    String longtitude;
    List<ItemTips> tips;

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

    public static DetailInformation from(ArrayList<String> list, ResponseItem item, String discription, List<ItemTips> tips){
        return new DetailInformation(
                list.get(0),
                list.get(1),
                item.getVenueItem().getRating().toString(),
                list.get(2),
                item.getVenueItem().getPrice().getCurrency(),
                discription,
                list.get(3),
                list.get(4)+"bg_64"+list.get(5),
                item.getVenueItem().getBestPhoto().getPrefix()+ item.getVenueItem().getBestPhoto().getWidth()+"x"+
                        item.getVenueItem().getBestPhoto().getHeight()+item.getVenueItem().getBestPhoto().getSuffix(),
                list.get(6),
                list.get(7),
                tips);
    }
}
