package com.example.foursquare.networking.venuelist.json;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Venue {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("location")
        @Expose
        private Location location;
        @SerializedName("categories")
        @Expose
        private List<Category> categories = null;
        @SerializedName("id")
        @Expose
        private String id;


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


        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public List<Category> getCategories() {
            return categories;
        }

        public void setCategories(List<Category> categories) {
            this.categories = categories;
        }


    }


