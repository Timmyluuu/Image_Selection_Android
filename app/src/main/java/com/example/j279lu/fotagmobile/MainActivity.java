package com.example.j279lu.fotagmobile;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.support.v7.widget.Toolbar;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    Toolbar mToolbar;
    RecyclerView mGridView;
    RecyclerView.LayoutManager mLayoutManager;
    MyAdapter myAdpter;
    boolean f;
    float currentrating;
    ToggleButton mToggle;
    ImageCollectionModel icm = new ImageCollectionModel(0);

    float [] ratings = {0,1,2,3,4,5,0,1,2,3,4,5};
    int [] image = {
            R.drawable.a, R.drawable.b,
            R.drawable.c, R.drawable.d,
            R.drawable.e, R.drawable.f,
            R.drawable.g, R.drawable.h,
            R.drawable.i, R.drawable.j,
            R.drawable.k, R.drawable.l};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.toolbar);
        mGridView = findViewById(R.id.recyclerView);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            mLayoutManager = new GridLayoutManager(this, 1);
        } else {
            mLayoutManager = new GridLayoutManager(this, 2);
        }
        mGridView.setLayoutManager(mLayoutManager);
        myAdpter = new MyAdapter(MainActivity.this, icm);
        mGridView.setAdapter(myAdpter);

        RatingBar filter = findViewById(R.id.ratingBar);
        ImageButton load = findViewById(R.id.imageButton1);
        ImageButton clear = findViewById(R.id.imageButton2);
        mToggle = findViewById(R.id.toggleButton);

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                icm = new ImageCollectionModel(0);
                ImageModel i0 = new ImageModel(image[0], ratings[0], icm);
                ImageModel i1 = new ImageModel(image[1], ratings[1], icm);
                ImageModel i2 = new ImageModel(image[2], ratings[2], icm);
                ImageModel i3 = new ImageModel(image[3], ratings[3], icm);
                ImageModel i4 = new ImageModel(image[4], ratings[4], icm);
                ImageModel i5 = new ImageModel(image[5], ratings[5], icm);
                ImageModel i6 = new ImageModel(image[6], ratings[6], icm);
                ImageModel i7 = new ImageModel(image[7], ratings[7], icm);
                ImageModel i8 = new ImageModel(image[8], ratings[8], icm);
                ImageModel i9 = new ImageModel(image[9], ratings[9], icm);
                ImageModel i10 = new ImageModel(image[10], ratings[10], icm);
                ImageModel i11 = new ImageModel(image[11], ratings[11], icm);
                icm.add_image(i0);
                icm.add_image(i1);
                icm.add_image(i2);
                icm.add_image(i3);
                icm.add_image(i4);
                icm.add_image(i5);
                icm.add_image(i6);
                icm.add_image(i7);
                icm.add_image(i8);
                icm.add_image(i9);
                icm.add_image(i10);
                icm.add_image(i11);
                mGridView = findViewById(R.id.recyclerView);
                myAdpter = new MyAdapter(MainActivity.this, icm);
                mGridView.setAdapter(myAdpter);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGridView = findViewById(R.id.recyclerView);
                icm.clear();
                myAdpter = new MyAdapter(MainActivity.this, icm);
                mGridView.setAdapter(myAdpter);
            }
        });

        filter.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                currentrating = v;
                if (!f) return;
                icm.filterImage(v);
                mGridView = findViewById(R.id.recyclerView);
                myAdpter = new MyAdapter(MainActivity.this, icm);
                mGridView.setAdapter(myAdpter);
            }
        });

        mToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                f = b;
                if(b) {
                    icm.filterImage(currentrating);
                    mGridView = findViewById(R.id.recyclerView);
                    myAdpter = new MyAdapter(MainActivity.this, icm);
                    mGridView.setAdapter(myAdpter);
                } else {
                    icm.reset();
                    mGridView = findViewById(R.id.recyclerView);
                    myAdpter = new MyAdapter(MainActivity.this, icm);
                    mGridView.setAdapter(myAdpter);
                }
            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloatArray("rating", ratings);
        outState.putIntArray("image", image);
        outState.putSerializable("icm", icm);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ratings = savedInstanceState.getFloatArray("rating");
        image = savedInstanceState.getIntArray("image");
        icm = (ImageCollectionModel) savedInstanceState.getSerializable("icm");
        myAdpter = new MyAdapter(MainActivity.this, icm);
        mGridView.setAdapter(myAdpter);
    }

}
