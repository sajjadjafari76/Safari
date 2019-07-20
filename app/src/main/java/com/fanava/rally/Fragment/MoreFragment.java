package com.fanava.rally.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fanava.rally.Interface.OnclickMore;
import com.fanava.rally.Model.ClassItem;
import com.fanava.rally.Model.ClassModel;
import com.fanava.rally.R;
import com.fanava.rally.Utils.EndlessRecyclerViewScrollListener;
import com.fanava.rally.Utils.Url;
import com.fanava.rally.adapter.CompetitionsAdapter;
import com.fanava.rally.adapter.RecyclerMoreAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fanava.rally.MainActivity.main;

public class MoreFragment extends Fragment implements OnclickMore {

    int mode;

    RecyclerView recyclerView;
    RecyclerMoreAdapter adapter;


    List<ClassItem> listItem;
    GridLayoutManager gridLayoutManager;
    ProgressBar progressBar;
    RelativeLayout parent;


    public MoreFragment(int mode) {
        this.mode = mode;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        main = true;
        View view = inflater.inflate(R.layout.fragment_more, container, false);


        recyclerView = view.findViewById(R.id.recycler);
        progressBar = view.findViewById(R.id.progress);
        parent = view.findViewById(R.id.parent);
        listItem = new ArrayList<>();


        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        sendRequestTakeover(0);
//        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(gridLayoutManager) {
//            @Override
//            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
//
//
////ارسال اطلاعات به سرور برحسب تعداد  دلخواه پرش
////array_list.size() تعداد ایتم های کنونی لود شده در ریسایکلر ویو
//
//                switch (mode) {
//                    case 0:
//                        sendRequestTakeover(listItem_online.size());
//                        break;
//                    case 1:
//                        sendRequestTakeover(listItem_last.size());
//                        break;
//                    case 2:
//                        sendRequestTakeover(listItem_after.size());
//                        break;
//                }
//            }
//        };

//        sendRequestToserver( int offset){
//            ret
////        } ;
//        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener() {
//            @Override
//            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
//
//            }
//        }

//        recyclerView.addOnScrollListener(scrollListener);


        return view;
    }

    public void sendRequestTakeover(final int count) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url.GlobalUrl + "Race/MainPageRace" +
                "?offset=" + count + "&count=30" + "&mode=" + mode, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonObject = new JSONArray(response);

//                    JSONArray jsonArray_last = new JSONArray(response);
                    for (int i = 0; i < jsonObject.length(); i++) {
                        listItem.add(new ClassItem(jsonObject.getJSONObject(i)));
                    }

//                    model.setListItem_last(listItem_last);
//                    JSONArray jsonArray_after = new JSONArray(jsonObject.getString("futureRace"));
//                    for (int i = 0; i < jsonArray_after.length(); i++) {
//                        listItem_after.add(new ClassItem(jsonArray_after.getJSONObject(i)));
//                    }
//                    model.setListItem_after(listItem_after);
//
//                    JSONArray jsonArray_online = new JSONArray(jsonObject.getString("runningRace"));
//                    for (int i = 0; i < jsonArray_online.length(); i++) {
//                        listItem_online.add(new ClassItem(jsonArray_online.getJSONObject(i)));
//                    }
//                    model.setListItem_online(listItem_online);


//                    switch (mode) {
//                        case 0:
                    recyclerView.setLayoutManager(gridLayoutManager);
                    CompetitionsAdapter adapter_online = new CompetitionsAdapter(getContext(), listItem, 0, 1);
                    recyclerView.setAdapter(adapter_online);
                    parent.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
//                            break;
//                        case 1:
//                            recyclerView.setLayoutManager(gridLayoutManager);
//                            CompetitionsAdapter adapter_last = new CompetitionsAdapter(getContext(), listItem_last, 1, 1);
//                            recyclerView.setAdapter(adapter_last);
//
//                            break;
//                        case 2:
//                            recyclerView.setLayoutManager(gridLayoutManager);
//                            CompetitionsAdapter adapter_after = new CompetitionsAdapter(getContext(), listItem_after, 2, 1);
//                            recyclerView.setAdapter(adapter_after);
//                            break;

//                    }


                } catch (JSONException e) {
                    parent.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                parent.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(stringRequest);
    }


    @Override
    public void onclick(int more) {


    }

    @Override
    public void onclickImage(int more, String image) {

    }
}
