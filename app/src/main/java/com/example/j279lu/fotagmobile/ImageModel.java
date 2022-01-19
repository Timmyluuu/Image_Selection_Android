package com.example.j279lu.fotagmobile;

/**
 * Created by TimmyLu on 22/07/2018.
 */

public class ImageModel {
    int image;
    float rating;
    ImageCollectionModel icm;


    public ImageModel(int i, float r, ImageCollectionModel ic) {
        image = i;
        rating = r;
        icm = ic;
    }

    public int getImage() {
        return image;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float r) {
        rating = r;

    }


}
