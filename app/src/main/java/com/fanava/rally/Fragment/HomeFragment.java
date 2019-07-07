package com.fanava.rally.Fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.fanava.rally.Model.ClassSlider;
import com.fanava.rally.R;
import com.fanava.rally.Utils.CustomVolleyRequest;
import com.fanava.rally.Utils.Url;
import com.fanava.rally.adapter.RecyclerMainAdapter;
import com.fanava.rally.adapter.SliderAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    RequestQueue rq;
    private List<ClassSlider> sliderImg;
    private SliderAdapter viewPagerAdapter;
    String url = Url.GlobalUrl+"";

    private ViewPager viewPager;
    private LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;

    RecyclerView recyclerView;
    RecyclerMainAdapter adapter;
    RelativeLayout re_Tournament_table;
    FragmentTransaction transaction ;
    private FragmentActivity myContext;
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
        adapter = new RecyclerMainAdapter(getContext(),false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
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
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_home));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_account));
//
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {

                    ClassSlider sliderUtils = new ClassSlider();

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        sliderUtils.setSliderImageUrl(jsonObject.getString("image_url"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    sliderImg.add(sliderUtils);

                }

                viewPagerAdapter = new SliderAdapter(sliderImg, getContext());

                viewPager.setAdapter(viewPagerAdapter);

                dotscount = viewPagerAdapter.getCount();
                dots = new ImageView[dotscount];

                for (int i = 0; i < dotscount; i++) {

                    dots[i] = new ImageView(getContext());
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_home));

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                    params.setMargins(0, 0, 0, 0);

                    sliderDotspanel.addView(dots[i], params);

                }

                dots[0].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_map));

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
                Toast.makeText(myContext, "sdfadf", Toast.LENGTH_SHORT).show();
                transaction.replace(R.id.main_container, new TournamentTableFragment()).addToBackStack(null);
                transaction.commit();


            }
        });
        return view;

    }

    @Override
    public void onAttach(Context context) {
        myContext=(FragmentActivity) context;
        super.onAttach(context);

    }

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
    }

}
