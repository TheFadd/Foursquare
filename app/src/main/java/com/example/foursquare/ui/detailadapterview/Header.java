package com.example.foursquare.ui.detailadapterview;

public class Header extends RecycleViewItem {

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
