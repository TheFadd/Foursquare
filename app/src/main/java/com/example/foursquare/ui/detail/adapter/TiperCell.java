package com.example.foursquare.ui.detail.adapter;

import com.example.foursquare.data.networking.venueitem.models.ItemTips;

public class TiperCell implements VenueRecyclerCell {

    private ItemTips itemTips;

    public ItemTips getItemTips() {
        return itemTips;
    }

    public void setItemTips(ItemTips itemTips) {
        this.itemTips = itemTips;
    }

    public TiperCell(ItemTips itemTips) {
        this.itemTips = itemTips;
    }
}
