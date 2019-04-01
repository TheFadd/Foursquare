package com.example.foursquare.detailAdapterView;

import com.example.foursquare.DetailInformation;

public class Header extends  RecycleViewItem {

    private DetailInformation detailInformation;

    public DetailInformation getDetailInformation() {
        return detailInformation;
    }

    public void setDetailInformation(DetailInformation detailInformation) {
        this.detailInformation = detailInformation;
    }

    public Header(DetailInformation detailInformation) {
        this.detailInformation = detailInformation;
    }
}
