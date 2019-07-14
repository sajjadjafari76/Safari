package com.fanava.rally.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.fanava.rally.Model.ItemCarSelect;
import com.fanava.rally.R;
import com.fanava.rally.Utils.RecyclerItemClickListener;
import com.fanava.rally.Utils.Url;
import com.fanava.rally.adapter.ItemFactorAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SelectCarFragment extends Fragment {

    int id;

    RecyclerView recyclerView;
    Button next;
    RelativeLayout parent;
    ProgressBar progressBar;
    String object;
    Bundle bundle = new Bundle();
    String[] itemsClass;

    FragmentManager fm = getFragmentManager();
    List<ItemCarSelect> list = new ArrayList<>();

    public SelectCarFragment(int id) {
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_car, container, false);

        recyclerView = view.findViewById(R.id.recycler);
        next = view.findViewById(R.id.next);
        parent = view.findViewById(R.id.parent);
        progressBar = view.findViewById(R.id.progress);
        fm = getFragmentManager();
        getData();
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int idCar =  list.get(position).id;
                SelectNavigateFragment selectNavigatFragment = new SelectNavigateFragment(idCar);
                selectNavigatFragment.setArguments(bundle);
                fm.beginTransaction().replace(R.id.fragment_factor, selectNavigatFragment).commit();
            }
        }));


        return view;
    }

    public void getData() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url.GlobalUrl + "GetOne", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                progressBar.setVisibility(View.GONE);
                parent.setVisibility(View.VISIBLE);


                bundle.putString("object", object);

                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                recyclerView.setAdapter(new ItemFactorAdapter(getContext()));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                progressBar.setVisibility(View.GONE);
                parent.setVisibility(View.VISIBLE);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id", String.valueOf(id));
                return map;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(stringRequest);
    }

}
