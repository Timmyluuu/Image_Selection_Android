package com.example.j279lu.fotagmobile;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Button;
import android.support.v7.widget.RecyclerView;

import java.util.List;
/**
 * Created by TimmyLu on 22/07/2018.
 */


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<ImageModel> images;
    private ImageCollectionModel imc;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImg;
        public Button mBut;
        public RatingBar mRat;
        public ViewHolder(View v) {
            super(v);
            mImg = v.findViewById(R.id.imageView);
            mBut = v.findViewById(R.id.resetButton);
            mRat = v.findViewById(R.id.ratingBar);
        }
    }

    public MyAdapter(Context c, ImageCollectionModel i) {
        images = i.getImages();
        imc = i;
        context = c;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview_image, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ImageModel im = images.get(position);

        holder.mImg.setImageResource(im.getImage());
        holder.mRat.setRating(im.getRating());

        holder.mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
                LayoutInflater inflater = (LayoutInflater) context.getSystemService
                        (Context.LAYOUT_INFLATER_SERVICE);
                View Myview = inflater.inflate(R.layout.single_image, null, false);
                ImageView m = (ImageView) Myview.findViewById(R.id.imageView3);
                m.setImageResource(im.getImage());
                mBuilder.setView(Myview);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        holder.mRat.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                 imc.setRating(v, position);
            }
        });

        holder.mBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imc.setRating(0, position);
                holder.mRat.setRating(0);
            }
        });

    }

    @Override
    public int getItemCount() {
        return images.size();
    }
}