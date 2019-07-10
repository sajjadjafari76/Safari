package com.fanava.rally.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fanava.rally.Model.ClassItem;
import com.fanava.rally.Model.ClassModel;
import com.fanava.rally.R;
import com.fanava.rally.adapter.CompetitionsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InfoTournament extends AppCompatActivity {

    TextView text_price, number_price, text_name, text_class, text_location, text_date, text_volume;
    CardView rule, galery;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_tournament);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getInt("id");
            sendRequestTakeover();
        }

        text_price = findViewById(R.id.text_price);
        number_price = findViewById(R.id.number_price);
        text_name = findViewById(R.id.text_name);
        text_class = findViewById(R.id.text_class);
        text_location = findViewById(R.id.text_location);
        text_date = findViewById(R.id.text_date);
        text_volume = findViewById(R.id.text_volume);
        rule = findViewById(R.id.rule);
        galery = findViewById(R.id.galery);
    }

    public void sendRequestTakeover() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://46.4.174.67:90/api/Race/MainPageRace", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

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
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }

}
