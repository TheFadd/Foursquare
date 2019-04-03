package com.example.foursquare.data.networking.venueitem.jsononeitem;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BestPhoto {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("prefix")
    @Expose
    private String prefix;
    @SerializedName("suffix")
    @Expose
    private String suffix;
    @SerializedName("width")
    @Expose
    private String width;

    @SerializedName("height")
    @Expose
    private String height;

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public BestPhoto(String id, String prefix, String suffix, String width, String height) {
        this.id = id;
        this.prefix = prefix;
        this.suffix = suffix;
        this.width = width;
        this.height = height;
    }
}
