package com.fanava.rally.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fanava.rally.Model.ClassInfoTournament;
import com.fanava.rally.R;
import com.fanava.rally.Utils.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfoTournament extends AppCompatActivity {

    TextView text_price, number_price, text_name, text_class, text_location, text_date, text_volume;
    CardView rule, gallery;
    Button btn_pay;
    int rece_id;
    private AlertDialog dialog;
    private AlertDialog.Builder builder;
    ProgressBar progressBar;
    RelativeLayout parent;
    ClassInfoTournament infoTournament;
    String ruleText = "";
    List<String> list_image;
    JSONArray array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_tournament);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            rece_id = bundle.getInt("id");
            sendRequestTakeover();
        }
        list_image = new ArrayList<>();
        builder = new AlertDialog.Builder(InfoTournament.this);

        text_price = findViewById(R.id.text_price);
        number_price = findViewById(R.id.number_price);
        text_name = findViewById(R.id.text_name);
        text_class = findViewById(R.id.text_class);
        text_location = findViewById(R.id.text_location);
        text_date = findViewById(R.id.text_date);
        text_volume = findViewById(R.id.text_volume);
        rule = findViewById(R.id.rule);
        gallery = findViewById(R.id.gallery);
        btn_pay = findViewById(R.id.btn_pay);
        parent = findViewById(R.id.parent);
        progressBar = findViewById(R.id.progress);
        final LayoutInflater inflater = getLayoutInflater();
        final View alertLayout = inflater.inflate(R.layout.dialog_rule, null);

        AlertDialog.Builder alert = new AlertDialog.Builder(InfoTournament.this);
        alert.setView(alertLayout);
        alert.setCancelable(true);
        final AlertDialog dialog = alert.create();

        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Button btn = alertLayout.findViewById(R.id.button);
                final CheckBox checkBox = alertLayout.findViewById(R.id.checkbox);
                TextView textView = alertLayout.findViewById(R.id.content);

                textView.setText(ruleText);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkBox.isChecked()) {

                            Intent intent = new Intent(getApplicationContext(), FactorActivity.class);
                            intent.putExtra("id", rece_id);
                            startActivity(intent);
                            dialog.dismiss();
                        } else {
                            Toast.makeText(getApplicationContext(), "با قوانین موافق نیستید !!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
            }
        });
        rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(),GalleryActivity.class);
                intent.putExtra("imageList", array.toString());
                startActivity(intent);

//                Uri path = Uri.parse("android.resource://irstit.transport/" + R.drawable.nerkh1);
//                Intent fullScreenIntent = new Intent(this, FullScreenImageActivity.class);
//                fullScreenIntent.setData(path);
//                startActivity(fullScreenIntent);
            }
        });
    }

    public void sendRequestTakeover() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url.GlobalUrl + "Race/getone?id=" + rece_id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    parent.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    JSONObject object = new JSONObject(response);

//                    infoTournament = new ClassInfoTournament(object);

                    text_price.setText(object.getString("strPrice"));
                    number_price.setText(object.getString("price"));
                    text_name.setText(object.getString("name"));
                    text_class.setText(object.getJSONObject("categoryID").getString("name"));
                    text_location.setText(object.getString("city"));
                    text_date.setText(object.getString("raceDate"));
                    text_volume.setText(object.getString("totalCapacityCount"));
                    ruleText = object.getString("rules");
//                    jsonArray = object.getString("images");
                    array = new JSONArray(object.getString("images"));
//                    for (int i = 0; i < array.length(); i++) {
//                        list_image.add(array.getString(i));
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
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id", String.valueOf(rece_id));
                return map;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }

}
