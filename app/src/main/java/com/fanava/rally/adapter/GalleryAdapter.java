package com.fanava.rally.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fanava.rally.Activity.FullScreenImageActivity;
import com.fanava.rally.Interface.OnclickMore;
import com.fanava.rally.Model.ClassModel;
import com.fanava.rally.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.Holder> {


    //    List<GetDateMainList> mDataset; // لیست داده هایی از نوع مدل phone که در اداپتر هستند
    //    Context context; // کانتکست لیست
    private Context context;

//    ClassModel item;
    boolean mode;
    FragmentTransaction transaction;
    OnclickMore onclickMore;
    List<String> item;
    OnclickMore onclick;


    public GalleryAdapter(Context context,List<String> item,OnclickMore onclick) { // تابع سازنده
//        this.mDataset = myDataset;
        this.context = context;
        this.item = item;
        this.onclick = onclick;
    }

    @Override
    public GalleryAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sample_gallery, parent, false);
        GalleryAdapter.Holder holder = new GalleryAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(GalleryAdapter.Holder holder, final int position) {

        holder.setIsRecyclable(false);

        Picasso.get().load(item.get(position))
                .error(R.drawable.ic_3)
                .into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick.onclickImage(position,item.get(position));
            }
        });


    }

    @Override
    public int getItemCount() {
        return 3;
    }


    class Holder extends RecyclerView.ViewHolder {

        ImageView image;


        Holder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);

        }
    }
}
