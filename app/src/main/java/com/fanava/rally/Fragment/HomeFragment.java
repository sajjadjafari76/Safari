package com.fanava.rally.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fanava.rally.Activity.InfoTournament;
import com.fanava.rally.Interface.OnclickMore;
import com.fanava.rally.Model.ClassItem;
import com.fanava.rally.Model.ClassModel;
import com.fanava.rally.Model.ClassSlider;
import com.fanava.rally.R;
import com.fanava.rally.Utils.CustomVolleyRequest;
import com.fanava.rally.Utils.RecyclerItemClickListener;
import com.fanava.rally.Utils.Url;
import com.fanava.rally.adapter.CompetitionsAdapter;
import com.fanava.rally.adapter.RecyclerMainAdapter;
import com.fanava.rally.adapter.SliderAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements OnclickMore {

    RequestQueue rq;
    private List<ClassSlider> sliderImg;
    private SliderAdapter viewPagerAdapter;
    String url = Url.GlobalUrl+"DataApp/Silder";

    private ViewPager viewPager;
    private LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;

    RecyclerView recyclerView;
    RecyclerMainAdapter adapter;
    RelativeLayout re_Tournament_table;
    FragmentTransaction transaction;
    private FragmentActivity myContext;


    List<ClassItem> listItem_last;
    List<ClassItem> listItem_after;
    List<ClassItem> listItem_online;

    ClassModel modelTwo = new ClassModel();

    NestedScrollView paren;
    ProgressBar progressBar;

    //
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        transaction = myContext.getSupportFragmentManager().beginTransaction();
        recyclerView = view.findViewById(R.id.recyclerView);
        re_Tournament_table = view.findViewById(R.id.re_Tournament_table);
        paren = view.findViewById(R.id.paren);
        progressBar = view.findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        paren.setVisibility(View.GONE);

        listItem_after = new ArrayList<>();
        listItem_last = new ArrayList<>();
        listItem_online = new ArrayList<>();

//        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Intent intent = new Intent(getContext(), InfoTournament.class);
//                switch (position) {
//                    case 0:
//                        intent.putExtra("id", listItem_online.get(position).id);
//                        Toast.makeText(getContext(), listItem_online.get(position).id + "", Toast.LENGTH_SHORT).show();
//                        startActivity(intent);
//                        break;
//                    case 1:
//                        intent.putExtra("id", listItem_last.get(position).id);
//                        Toast.makeText(getContext(), listItem_last.get(position).id + "", Toast.LENGTH_SHORT).show();
//                        startActivity(intent);
//                        break;
//                    case 2:
//                        intent.putExtra("id", listItem_after.get(position).id);
//                        Toast.makeText(getContext(), listItem_after.get(position).id + "", Toast.LENGTH_SHORT).show();
//                        startActivity(intent);
//                        break;
//                }
//            }
//        }));


//****************************************** Slider ***************************************************************
        rq = CustomVolleyRequest.getInstance(getContext()).getRequestQueue();

        sliderImg = new ArrayList<>();

        viewPager = (ViewPager) view.findViewById(R.id.viewPager);

        sliderDotspanel = (LinearLayout) view.findViewById(R.id.SliderDots);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.bg_unselect));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.bg_select));
//
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        final StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {

                        ClassSlider sliderUtils = new ClassSlider();

//                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        sliderUtils.setSliderImageUrl(jsonArray.getString(i));


                        sliderImg.add(sliderUtils);

//                        Log.i("adasdasasdfi", "onResponse: "+jsonObject);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                viewPagerAdapter = new SliderAdapter(sliderImg, getContext());

                viewPager.setAdapter(viewPagerAdapter);

                dotscount = viewPagerAdapter.getCount();
                dots = new ImageView[dotscount];

                for (int i = 0; i < dotscount; i++) {

                    dots[i] = new ImageView(getContext());
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.bg_unselect));

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                    params.setMargins(0, 0, 0, 0);

                    sliderDotspanel.addView(dots[i], params);

                }

                dots[0].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.bg_select));
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });

        CustomVolleyRequest.getInstance(getContext()).addToRequestQueue(jsonArrayRequest);

        ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };

//**************************************************************************************************************


        re_Tournament_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!listItem_last.isEmpty()) {
                    transaction.replace(R.id.main_container, new TournamentTableFragment(modelTwo)).addToBackStack(null);
                    transaction.commit();
                }
            }
        });


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.GlobalUrl+"DataApp/Races", new Response.Listener<String>() {
            @Override
            public void onResponse(String respons) {

                progressBar.setVisibility(View.VISIBLE);
                paren.setVisibility(View.GONE);

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

                    adapter = new RecyclerMainAdapter(getContext(), false, model, HomeFragment.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                    recyclerView.setAdapter(adapter);
                    modelTwo = model;
                    progressBar.setVisibility(View.GONE);
                    paren.setVisibility(View.VISIBLE);

                } catch (JSONException e) {
                    progressBar.setVisibility(View.GONE);
                    paren.setVisibility(View.VISIBLE);
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                progressBar.setVisibility(View.GONE);
                paren.setVisibility(View.VISIBLE);
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(stringRequest);

        return view;

    }

    @Override
    public void onAttach(Context context) {
        myContext = (FragmentActivity) context;
        super.onAttach(context);

    }

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
    }

    @Override
    public void onclick(int more) {

        switch (more) {
            case 0:
                transaction.replace(R.id.main_container, new MoreFragment(0)).addToBackStack(null);
                transaction.commit();
                break;
            case 1:
                transaction.replace(R.id.main_container, new MoreFragment(1)).addToBackStack(null);
                transaction.commit();
                break;
            case 2:
                transaction.replace(R.id.main_container, new MoreFragment(2)).addToBackStack(null);
                transaction.commit();
                break;
        }
    }

    @Override
    public void onclickImage(int more, String image) {

    }
}
