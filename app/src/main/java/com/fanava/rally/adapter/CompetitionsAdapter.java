package com.fanava.rally.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fanava.rally.Activity.InfoTournament;
import com.fanava.rally.Model.ClassItem;
import com.fanava.rally.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CompetitionsAdapter extends RecyclerView.Adapter<CompetitionsAdapter.Holder> {


    List<ClassItem> mDataset;
    //    Context context; // کانتکست لیست
    private Context context;
    int image;
    int show;

    public CompetitionsAdapter(Context context, List<ClassItem> mDataset, int image, int show) { // تابع سازنده
        this.mDataset = mDataset;
        this.image = image;
        this.context = context;
        this.show = show;
    }

    @Override
    public CompetitionsAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (show == 1) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.sample_item_competitions_grid, parent, false);
            CompetitionsAdapter.Holder holder = new CompetitionsAdapter.Holder(view);
            return holder;
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.sample_item_competitions, parent, false);
            CompetitionsAdapter.Holder holder = new CompetitionsAdapter.Holder(view);
            return holder;
        }

    }

    @Override
    public void onBindViewHolder(CompetitionsAdapter.Holder holder, final int position) {

//        holder.setIsRecyclable(false);
        switch (image) {
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

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InfoTournament.class);
                intent.putExtra("id", mDataset.get(position).id);
                Toast.makeText(context, mDataset.get(position).id + "", Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    class Holder extends RecyclerView.ViewHolder {


        ImageView imageView;
        TextView competitionNumber, location, date;
        CardView parent;

        Holder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            competitionNumber = itemView.findViewById(R.id.competitionNumber);
            location = itemView.findViewById(R.id.location);
            date = itemView.findViewById(R.id.date);
            parent = itemView.findViewById(R.id.parent);
        }
    }

}
