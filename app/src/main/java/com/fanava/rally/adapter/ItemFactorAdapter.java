package com.fanava.rally.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fanava.rally.Interface.OnclickMore;
import com.fanava.rally.Model.ClassModel;
import com.fanava.rally.R;

public class ItemFactorAdapter extends RecyclerView.Adapter<ItemFactorAdapter.Holder> {


    private Context context;

    ClassModel item;
    boolean mode;
    FragmentTransaction transaction;
    OnclickMore onclickMore;

    public ItemFactorAdapter(Context context) { // تابع سازنده
//        this.mDataset = myDataset;
        this.context = context;
        this.mode = mode;
        this.item = item;
        this.onclickMore = onclickMore;
    }

    @Override
    public ItemFactorAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sample_recylcer_main, parent, false);
        ItemFactorAdapter.Holder holder = new ItemFactorAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemFactorAdapter.Holder holder, int position) {

//        holder.setIsRecyclable(false);
        switch (position) {
            case 0:
                holder.title.setText("مسابقات در حال برگزاری");
                if (mode) {
                    holder.re.setBackgroundColor(context.getResources().getColor(R.color.online));
                    holder.title.setTextColor(context.getResources().getColor(R.color.white));
                    holder.more.setTextColor(context.getResources().getColor(R.color.white));
                }
                holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, true));
                CompetitionsAdapter adapter_online = new CompetitionsAdapter(context, item.getListItem_online(),0,0);
                holder.recyclerView.setAdapter(adapter_online);

                holder.more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onclickMore.onclick(0);
                    }
                });
                break;
            case 1:
                holder.title.setText("مسابقات گذشته");
                if (mode) {
                    holder.re.setBackgroundColor(context.getResources().getColor(R.color.last));
                    holder.title.setTextColor(context.getResources().getColor(R.color.white));
                    holder.more.setTextColor(context.getResources().getColor(R.color.white));
                }
                holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, true));
                CompetitionsAdapter adapter_last = new CompetitionsAdapter(context, item.getListItem_last(),1,0);
                holder.recyclerView.setAdapter(adapter_last);

                holder.more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onclickMore.onclick(1);
                    }
                });
                break;
            case 2:
                holder.title.setText("مسابقات آینده");
                if (mode) {
                    holder.re.setBackgroundColor(context.getResources().getColor(R.color.after));
                    holder.title.setTextColor(context.getResources().getColor(R.color.white));
                    holder.more.setTextColor(context.getResources().getColor(R.color.white));
                }
                holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, true));
                CompetitionsAdapter adapter_after = new CompetitionsAdapter(context, item.getListItem_after(),2,0);
                holder.recyclerView.setAdapter(adapter_after);

                holder.more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onclickMore.onclick(2);
                    }
                });
                break;
        }


    }

    @Override
    public int getItemCount() {
        return 3;
    }


    class Holder extends RecyclerView.ViewHolder {

        TextView title, more;
        RecyclerView recyclerView;
        RelativeLayout re;


        Holder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            re = itemView.findViewById(R.id.re);
            more = itemView.findViewById(R.id.more);
            recyclerView = itemView.findViewById(R.id.recycler);
        }
    }
}
