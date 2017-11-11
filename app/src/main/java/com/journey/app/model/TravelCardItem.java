package com.journey.app.model;

public class TravelCardItem extends CardItem {

    public Travel travel;

    public TravelCardItem(Travel travel) {
        type = TRAVEL;
        this.travel = travel;
    }

}
