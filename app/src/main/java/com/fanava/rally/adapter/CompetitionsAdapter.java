package com.fanava.rally.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fanava.rally.Model.ClassItem;
import com.fanava.rally.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CompetitionsAdapter extends RecyclerView.Adapter<CompetitionsAdapter.Holder> {


    List<ClassItem> mDataset;
    //    Context context; // کانتکست لیست
    private Context context;
    int image;

    public CompetitionsAdapter(Context context, List<ClassItem> mDataset,int image) { // تابع سازنده
        this.mDataset = mDataset;
        this.image = image;
        this.context = context;
    }

    @Override
    public CompetitionsAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sample_item_competitions, parent, false);
        CompetitionsAdapter.Holder holder = new CompetitionsAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CompetitionsAdapter.Holder holder, int position) {

        holder.setIsRecyclable(false);
        switch (image){
            case 0:
                Picasso.get().load(mDataset.get(position).image)
                        .error(R.drawable.ic_1)
                        .into(holder.imageView);
                break;
            case 1:
                Picasso.get().load(mDataset.get(position).image)
                        .error(R.drawable.ic_2)
                        .into(holder.imageView);
                break;
            case 2:
                Picasso.get().load(mDataset.get(position).image)
                        .error(R.drawable.ic_3)
                        .into(holder.imageView);
                break;
        }
        holder.competitionNumber.setText(mDataset.get(position).competitionNumber);
        holder.location.setText(mDataset.get(position).location);
        holder.date.setText(mDataset.get(position).date);




    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    class Holder extends RecyclerView.ViewHolder {


        ImageView imageView;
        TextView competitionNumber, location, date;

        Holder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            competitionNumber = itemView.findViewById(R.id.competitionNumber);
            location = itemView.findViewById(R.id.location);
            date = itemView.findViewById(R.id.date);
        }
    }

}
