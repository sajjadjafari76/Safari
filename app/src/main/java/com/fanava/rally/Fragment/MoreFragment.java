package com.fanava.rally.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class MoreFragment extends Fragment implements OnclickMore {

    int mode;

    RecyclerView recyclerView;
    RecyclerMoreAdapter adapter;


    List<ClassItem> listItem_last;
    List<ClassItem> listItem_after;
    List<ClassItem> listItem_online;
    GridLayoutManager gridLayoutManager;

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

        View view = inflater.inflate(R.layout.fragment_more, container, false);

        recyclerView = view.findViewById(R.id.recycler);
        listItem_after = new ArrayList<>();
        listItem_last = new ArrayList<>();
        listItem_online = new ArrayList<>();

        gridLayoutManager = new GridLayoutManager(getContext(), 3);
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

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url.GlobalUrl+"MainPageRace", new Response.Listener<String>() {
            @Override
            public void onResponse(String respons) {

                try {
                    JSONObject jsonObject = new JSONObject(respons);
                    ClassModel model = new ClassModel();

                    JSONArray jsonArray_last = new JSONArray(jsonObject.getString("finishedRace"));
                    for (int i = 0; i < jsonArray_last.length(); i++) {
                        listItem_last.add(new ClassItem(jsonArray_last.getJSONObject(i)));
                    }

                    model.setListItem_last(listItem_last);
                    JSONArray jsonArray_after = new JSONArray(jsonObject.getString("futureRace"));
                    for (int i = 0; i < jsonArray_after.length(); i++) {
                        listItem_after.add(new ClassItem(jsonArray_after.getJSONObject(i)));
                    }
                    model.setListItem_after(listItem_after);

                    JSONArray jsonArray_online = new JSONArray(jsonObject.getString("runningRace"));
                    for (int i = 0; i < jsonArray_online.length(); i++) {
                        listItem_online.add(new ClassItem(jsonArray_online.getJSONObject(i)));
                    }
                    model.setListItem_online(listItem_online);


                    switch (mode) {
                        case 0:
                            recyclerView.setLayoutManager(gridLayoutManager);
                            CompetitionsAdapter adapter_online = new CompetitionsAdapter(getContext(), listItem_online, 0, 1);
                            recyclerView.setAdapter(adapter_online);
                            break;
                        case 1:
                            recyclerView.setLayoutManager(gridLayoutManager);
                            CompetitionsAdapter adapter_last = new CompetitionsAdapter(getContext(), listItem_last, 1, 1);
                            recyclerView.setAdapter(adapter_last);

                            break;
                        case 2:
                            recyclerView.setLayoutManager(gridLayoutManager);
                            CompetitionsAdapter adapter_after = new CompetitionsAdapter(getContext(), listItem_after, 2, 1);
                            recyclerView.setAdapter(adapter_after);
                            break;

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("count", "30");
                map.put("offset", String.valueOf(count));
                map.put("mode", String.valueOf(mode));
                return map;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(stringRequest);
    }


    @Override
    public void onclick(int more) {


    }
}
