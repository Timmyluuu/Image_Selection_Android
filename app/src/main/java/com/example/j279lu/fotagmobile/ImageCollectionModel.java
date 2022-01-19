package com.example.j279lu.fotagmobile;

/**
 * Created by TimmyLu on 22/07/2018.
 */

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class ImageCollectionModel implements Serializable{
    List<ImageModel> images = new ArrayList<ImageModel>();
    List<ImageModel> temp = new ArrayList<ImageModel>();
    float rating;

    public ImageCollectionModel(int cr) {
        rating = cr;
    }

    public void add_image(ImageModel im) {
        temp.add(im);
        images.add(im);
    }

    public List<ImageModel> getImages() {
        return images;
    }

    public void setRating(float r, int pos) {
        images.get(pos).setRating(r);
    }

    public float getRating() {
        return rating;
    }

    public void clear() {
        images.clear();
    }

    public void filterImage(float r) {
        images.clear();
        for(ImageModel m:temp) {
            if (m.getRating() >= r) {
                images.add(m);
            }
        }
    }

    public void reset() {
        images.clear();
        for(ImageModel m:temp) {
            images.add(m);
        }
    }
}
