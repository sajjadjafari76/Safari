package com.fanava.rally.adapter;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fanava.rally.Model.ClassItem;
import com.fanava.rally.R;
import com.fanava.rally.Utils.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecyclerMainAdapter extends RecyclerView.Adapter<RecyclerMainAdapter.Holder> {


    //    List<GetDateMainList> mDataset; // لیست داده هایی از نوع مدل phone که در اداپتر هستند
    //    Context context; // کانتکست لیست
    private Context context;

    List<ClassItem> listItem_last;
    List<ClassItem> listItem_after;
    List<ClassItem> listItem_online;
    boolean mode;
    public RecyclerMainAdapter(Context context,boolean mode) { // تابع سازنده
//        this.mDataset = myDataset;
        this.context = context;
        this.mode = mode;
    }

    @Override
    public RecyclerMainAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sample_recylcer_main, parent, false);
        RecyclerMainAdapter.Holder holder = new RecyclerMainAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerMainAdapter.Holder holder, int position) {

        holder.setIsRecyclable(false);
        listItem_after = new ArrayList<>();
        listItem_last = new ArrayList<>();
        listItem_online = new ArrayList<>();

        switch (position) {
            case 0:
                holder.title.setText("مسابقات در حال برگزاری");
                if (mode) {
                holder.re.setBackgroundColor(context.getResources().getColor(R.color.online));
                }
                holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, true));
                CompetitionsAdapter adapter_online = new CompetitionsAdapter(context,listItem_online);
                holder.recyclerView.setAdapter(adapter_online);
                break;
            case 1:
                holder.title.setText("مسابقات گذشته");
                if (mode) {
                    holder.re.setBackgroundColor(context.getResources().getColor(R.color.last));
                }
                holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, true));
                CompetitionsAdapter adapter_last = new CompetitionsAdapter(context,listItem_last);
                holder.recyclerView.setAdapter(adapter_last);
                break;
            case 2:
                holder.title.setText("مسابقات آینده");
                if (mode) {
                    holder.re.setBackgroundColor(context.getResources().getColor(R.color.after));
                }
                holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, true));
                CompetitionsAdapter adapter_after = new CompetitionsAdapter(context,listItem_last);
                holder.recyclerView.setAdapter(adapter_after);
                break;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url.GlobalUrl + "", new Response.Listener<String>() {
            @Override
            public void onResponse(String respons) {

                try {
                    JSONObject jsonObject = new JSONObject(respons);
//                    JSONObject object_last = jsonArray.getJSONObject(0);
//                    JSONObject object_after = jsonArray.getJSONObject(1);
//                    JSONObject object_online = jsonArray.getJSONObject(2);

                    JSONArray jsonArray_last = new JSONArray(jsonObject.getJSONArray("finishedRace"));
                    for (int i = 0; i < jsonArray_last.length(); i++) {
                        listItem_last.add(new ClassItem(jsonArray_last.getJSONObject(i)));
                    }

                    JSONArray jsonArray_after = new JSONArray(jsonObject.getJSONArray("futureRace"));
                    for (int i = 0; i < jsonArray_after.length(); i++) {
                        listItem_after.add(new ClassItem(jsonArray_after.getJSONObject(i)));
                    }

                    JSONArray jsonArray_online = new JSONArray(jsonObject.getJSONArray("runningRace"));
                    for (int i = 0; i < jsonArray_online.length(); i++) {
                        listItem_online.add(new ClassItem(jsonArray_online.getJSONObject(i)));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);





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