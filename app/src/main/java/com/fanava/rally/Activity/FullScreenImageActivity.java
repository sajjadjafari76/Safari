package com.fanava.rally.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.fanava.rally.R;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

public class FullScreenImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);

        PhotoView fullScreenImageView = findViewById(R.id.fullScreenImageView);
        Intent callingActivityIntent = getIntent();
        if (callingActivityIntent != null) {
            Uri imageUri = callingActivityIntent.getData();
            if (imageUri != null && fullScreenImageView != null) {
                Picasso.get()
                        .load(imageUri)
                        .into(fullScreenImageView);
            }
        }

    }
}
