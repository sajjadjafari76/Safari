package com.fanava.rally.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fanava.rally.R;
import com.fanava.rally.Utils.Statics;
import com.fanava.rally.Utils.Upload;
import com.fanava.rally.adapter.AdapterListeners;
import com.fanava.rally.adapter.carAdapter;
import com.fanava.rally.server.Listeners;
import com.fanava.rally.server.ShowData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {
public EditText txt_username;
public EditText txt_password;
public EditText txt_name;
public EditText txt_code;
public EditText txt_family;
public CircleImageView img_profile;
public RecyclerView rec_car;
    public Button btn_register;
    public Button btn_car;
///
    public Uri uri_profile;
///////
   public ShowData showData;
    @Override
    protected void onResume() {
        super.onResume();
        rec_car.setItemAnimator(new DefaultItemAnimator());
        rec_car.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rec_car.setAdapter(new carAdapter(getApplicationContext(), Statics.viewList, new AdapterListeners.CarListener() {
            @Override
            public void onClick(int ID) {
                Intent intent=new Intent(getApplicationContext(),CarActivity.class);
                intent.putExtra("position",ID);
                startActivity(intent);
            }

            @Override
            public void onRemove(final int ID) {
                new AlertDialog.Builder(RegisterActivity.this)
                        .setTitle("جذف")
                        .setMessage("آیا مطمئن هستید؟")
                        .setPositiveButton("بله", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Statics.viewList.remove(ID);
                                rec_car.getAdapter().notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("خیر", null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        }));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ثبت نام راننده");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ////
         txt_username=findViewById(R.id.txt_username);
        txt_password=findViewById(R.id.txt_password);
         txt_name=findViewById(R.id.txt_name);
         txt_code=findViewById(R.id.txt_code);
         txt_family=findViewById(R.id.txt_family);
         img_profile=findViewById(R.id.img_profile);
         rec_car=findViewById(R.id.rec_car);
         btn_register=findViewById(R.id.btn_register);
         btn_car=findViewById(R.id.btn_car);
         ////
        btn_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),CarActivity.class);

                startActivity(intent);
            }
        });
img_profile.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
       // intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent,"Select Picture"),1);
    }
});
btn_register.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
showData=new ShowData();
        Map<String, String> jsonParams = new HashMap<String, String>();
        jsonParams.put("username",txt_username.getText().toString());
        jsonParams.put("password",txt_password.getText().toString());
        jsonParams.put("name",txt_name.getText().toString());
        jsonParams.put("family",txt_family.getText().toString());
        jsonParams.put("nationCode",txt_code.getText().toString());
        JSONObject jsonObject=new JSONObject(jsonParams);
showData.insert(getApplicationContext(), "racer/insert", jsonObject, new Listeners.OnSuccessListener() {
    @Override
    public void onSuccess(JSONArray jsonObject) {
        try {
        if(uri_profile!=null)
        {

                new  Upload().DoUpload(getApplicationContext(),"racer/upload",uri_profile,jsonObject.getJSONObject(0).getString("id"));

        }
if(Statics.viewList.size()>0)
{
    for(int i=0;i<Statics.viewList.size();i++) {
        Map<String, String> jsonParams = new HashMap<String, String>();
        jsonParams.put("name", Statics.viewList.get(i).getCarName());
        jsonParams.put("model",Statics.viewList.get(i).getModel());
        jsonParams.put("color", Statics.viewList.get(i).getColor());
        jsonParams.put("racerId", String.valueOf(Statics.viewList.get(i).getDriverId()));
        jsonParams.put("nationCode", txt_code.getText().toString());
        JSONObject jsonObject1 = new JSONObject(jsonParams);
        showData.insert(getApplicationContext(), "car/insert", jsonObject1, new Listeners.OnSuccessListener() {
            @Override
            public void onSuccess(JSONArray jsonObject) {

            }

            @Override
            public void onError(String error) {

            }
        });
    }

}
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(String error) {

    }
});
    }
});

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==16908332)
            finish();


        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK
                && null != data) {
            uri_profile=data.getData();
            img_profile.setImageURI(data.getData());

        }

    }
}
