package fr.esilvandroid.android_project;

public class OffersModel {
    private Offer offer;
    //State of the item
    private boolean expanded;

    public OffersModel(Offer offer){
        this.offer = offer;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isExpanded() {
        return expanded;
    }
}