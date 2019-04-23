package com.example.foursquare.ui.detail.adapter;

import com.example.foursquare.ui.detail.DetailInformation;

public class HeaderCell implements VenueRecyclerCell {

    private DetailInformation detailInformation;

    public DetailInformation getDetailInformation() {
        return detailInformation;
    }

    public void setDetailInformation(DetailInformation detailInformation) {
        this.detailInformation = detailInformation;
    }

    public HeaderCell(DetailInformation detailInformation) {
        this.detailInformation = detailInformation;
    }
}
