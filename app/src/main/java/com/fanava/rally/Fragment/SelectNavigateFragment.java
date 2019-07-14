package com.fanava.rally.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.fanava.rally.Utils.Url;
import com.fanava.rally.adapter.ItemFactorAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectNavigateFragment extends Fragment {


    int idCar;


    RecyclerView recyclerView;
    Button next;
    RelativeLayout parent;
    ProgressBar progressBar;
    String object;
    Bundle bundle = new Bundle();

    FragmentManager fm = getFragmentManager();
    List<ItemCarSelect> list = new ArrayList<>();
    public SelectNavigateFragment(int idCar) {
        this.idCar = idCar;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_navigat, container, false);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(new ItemFactorAdapter(getContext()));
        return view;
    }

}
