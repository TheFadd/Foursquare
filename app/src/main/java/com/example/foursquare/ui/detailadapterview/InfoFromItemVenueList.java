package com.example.foursquare.ui.detailadapterview;

public class InfoFromItemVenueList {

    private String id;
    private String name;
    private String shorName;
    private String adressWithDistance;
    private String prefix;
    private String syfix;
    private String latitude;
    private String longtitude;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShorName() {
        return shorName;
    }

    public void setShorName(String shorName) {
        this.shorName = shorName;
    }

    public String getAdressWithDistance() {
        return adressWithDistance;
    }

    public void setAdressWithDistance(String adressWithDistance) {
        this.adressWithDistance = adressWithDistance;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public InfoFromItemVenueList(String id, String name, String shorName, String adressWithDistance, String prefix, String syfix, String latitude, String longtitude) {
        this.id = id;
        this.name = name;
        this.shorName = shorName;
        this.adressWithDistance = adressWithDistance;
        this.prefix = prefix;
        this.syfix = syfix;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }
}
