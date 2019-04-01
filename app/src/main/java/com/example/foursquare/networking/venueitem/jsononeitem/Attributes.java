package com.example.foursquare.networking.venueitem.jsononeitem;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Attributes {

        @SerializedName("groups")
        @Expose
        private List<AttributesGroup> attributesGroups = null;

        public List<AttributesGroup> getAttributesGroups() {
            return attributesGroups;
        }

        public void setAttributesGroups(List<AttributesGroup> attributesGroups) {
            this.attributesGroups = attributesGroups;
        }

    }