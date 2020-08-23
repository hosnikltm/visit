package com.example.visit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView mImageView;
    TextView mTextView;

    int[] indexImage = {
            R.drawable.beach,
            R.drawable.bike,
            R.drawable.football,
            R.drawable.museum,
            R.drawable.park,
            R.drawable.restaurant,
            R.drawable.running,
            R.drawable.shop,
            R.drawable.swimming,
            R.drawable.walking
    };
    private  String [] name_Image = {
            "Beach",
            "Bike",
            "Football",
            "Museum",
            "Park",
            "Restaurant",
            "Running",
            "Shop",
            "Swimming",
            "Walking"
    };
    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final String BUNDLE_CURRENT_INDEX = "BUNDLE_CURRENT_INDEX";
    private int indexVisit =-1;
    private Random mRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = findViewById(R.id.imageView);
        mRandom = new Random();
        mTextView = findViewById(R.id.txt_name);

    }

    public void buttonNext(View view) {

        String s = "لقد وصلت الي النهاية اصورا";

        try {
            if (indexVisit< indexImage.length+1 ) {
               ++indexVisit;
               showImage();
            } else {
                Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        }
    }


    public void buttonRandom(View view) {
        indexVisit = mRandom.nextInt(indexImage.length);
        showImage();
    }

    public void buttonPrevious(View view) {

        String s = "لقد وصلت الي البداية الصور";

        try {
            if (indexVisit > 0) {
                --indexVisit;
               showImage();
            } else {
                Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        }
    }
    private void showImage() {
        Drawable drawable = ContextCompat.getDrawable(this, indexImage[indexVisit]);
        mImageView.setImageDrawable(drawable);
        mTextView.setText(name_Image[indexVisit]);

    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_CURRENT_INDEX, indexVisit);
        Log.i(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        indexVisit = savedInstanceState.getInt(BUNDLE_CURRENT_INDEX);
        if (indexVisit != -1) {
            showImage();
        }
        Log.i(TAG, "onRestoreInstanceState");
    }
}