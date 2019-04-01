package com.example.foursquare.detailAdapterView;

import com.example.foursquare.networking.venueitem.jsononeitem.ItemTips;

public class Tiper extends  RecycleViewItem {

    private ItemTips itemTips;

    public ItemTips getItemTips() {
        return itemTips;
    }

    public void setItemTips(ItemTips itemTips) {
        this.itemTips = itemTips;
    }

    public Tiper(ItemTips itemTips) {
        this.itemTips = itemTips;
    }
}
