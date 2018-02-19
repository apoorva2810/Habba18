package com.acharya.habba2k18.MainMenu;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import com.acharya.habba2k18.R;
import com.bumptech.glide.Glide;
import com.special.ResideMenu.ResideMenu;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.io.IOException;

public class HomeFragment extends Fragment {

    private View parentView;
    private ImageView imageView1;
    private CarouselView carouselView;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public static boolean location_permission = false;

    private String[] sampleImages = {"http://acharyahabba.in/habba18/images/slider1.jpg","http://acharyahabba.in/habba18/images/slider2.jpg","http://acharyahabba.in/habba18/images/slider3.jpg","http://acharyahabba.in/habba18/images/slider4.jpg"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.home, container, false);
        setUpViews();
        return parentView;

    }

    private void setUpViews() {
        MainActivity parentActivity = (MainActivity) getActivity();



        carouselView = (CarouselView) parentView.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                Glide.with(getContext()).load(sampleImages[position]).into(imageView);
            }
        });


        imageView1 = (ImageView)parentView.findViewById(R.id.imageView11);
        try {
            final pl.droidsonroids.gif.GifDrawable gifFromResource = new pl.droidsonroids.gif.GifDrawable( getResources(), R.drawable.image_gif);
            imageView1.setImageDrawable(gifFromResource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // do nothing
            }
        });

    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(getActivity())
                        .setTitle("Permission")
                        .setMessage("Permission required to operate maps related features of this app")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }



}
