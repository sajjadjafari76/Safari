package com.fanava.rally.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanava.rally.Interface.OnclickMore;
import com.fanava.rally.R;
import com.fanava.rally.adapter.GalleryAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity implements OnclickMore {

    RecyclerView recyclerView;

    String list;
    JSONArray jsonArray;
    List<String> listImage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_gallery);

        listImage = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_galley);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            list = bundle.getString("imageList");
            try {
                jsonArray = new JSONArray(list);
                for (int i = 0; i < jsonArray.length(); i++) {
                    listImage.add(jsonArray.getString(i));
                }


                recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 2));
                recyclerView.setAdapter(new GalleryAdapter(getBaseContext(), listImage,this));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }


    @Override
    public void onclick(int more) {


    }

    @Override
    public void onclickImage(int more, String image) {
        Uri path = Uri.parse(image);
        Intent fullScreenIntent = new Intent(this, FullScreenImageActivity.class);
        fullScreenIntent.setData(path);
        startActivity(fullScreenIntent);
    }
}
