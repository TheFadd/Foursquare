package com.example.foursquare.ui.detail;

public class TotalInformation {

    private String id;
    private String name;
    private String shortName;
    private String addressWithDistance;
    private String prefix;
    private String suffix;
    private double latitude;
    private double longitude;

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getAddressWithDistance() {
        return addressWithDistance;
    }

    public void setAddressWithDistance(String addressWithDistance) {
        this.addressWithDistance = addressWithDistance;
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

    public TotalInformation(String id, String name, String shortName, String addressWithDistance, String prefix, String suffix, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.addressWithDistance = addressWithDistance;
        this.prefix = prefix;
        this.suffix = suffix;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
