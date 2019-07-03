package com.fanava.rally.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.fanava.rally.Model.carModelView;
import com.fanava.rally.R;
import com.fanava.rally.Utils.Statics;
import com.fanava.rally.server.ShowData;

public class CarActivity extends AppCompatActivity {
public ImageView img_pelak;
public ImageView img_bimeh;
public ImageView img_car;
public TextView txt_name;
public TextView txt_model;
public TextView txt_color;
public Button btn_submit;
///
    public Uri uri_car;
    public Uri uri_pelak;
    public Uri uri_bimeh;
    ///////
public int position=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        getSupportActionBar().setTitle("ثبت اتومبیل");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //
         img_pelak=findViewById(R.id.img_pelak);
         img_bimeh=findViewById(R.id.img_bimeh);
         img_car=findViewById(R.id.img_car);
         txt_name=findViewById(R.id.txt_name);
         txt_model=findViewById(R.id.txt_model);
         txt_color=findViewById(R.id.txt_color);
         btn_submit=findViewById(R.id.btn_submit);
         //
        position=getIntent().getIntExtra("position",-1);
        if(position>=0)
        {
            img_pelak.setImageURI(Statics.viewList.get(position).getCar_pelak());
            img_bimeh.setImageURI(Statics.viewList.get(position).getCar_bimeh());
            img_car.setImageURI(Statics.viewList.get(position).getCar_pic());
            txt_name.setText(Statics.viewList.get(position).getCarName());
            txt_model.setText(Statics.viewList.get(position).getModel());
            txt_color.setText(Statics.viewList.get(position).getColor());
        }

        img_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                // intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent,"انتخاب عکس ماشین"),1);
            }
        });
        img_bimeh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                // intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent,"انتخاب عکس بیمه"),2);
            }
        });
img_pelak.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        // intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent,"انتخاب عکس پلاک"),3);
    }
});
btn_submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        carModelView model=new carModelView();
        model.setColor(txt_color.getText().toString());
        model.setCarName(txt_name.getText().toString());
        model.setModel(txt_model.getText().toString());
        model.setCar_bimeh(uri_bimeh);
        model.setCar_pelak(uri_pelak);
        model.setCar_pic(uri_car);
        if(position>=0){
            Statics.viewList.set(position,model);
        }
else
        Statics.viewList.add(model);
        finish();
    }
});
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK//عکس ماشین
                && null != data) {
            uri_car=data.getData();
            img_car.setImageURI(data.getData());

        }
        if (requestCode == 2 && resultCode == RESULT_OK//عکس بیمه
                && null != data) {
            uri_bimeh=data.getData();
            img_bimeh.setImageURI(data.getData());

        }
        if (requestCode == 3 && resultCode == RESULT_OK//عکس بیمه
                && null != data) {
            uri_pelak=data.getData();
            img_pelak.setImageURI(data.getData());

        }
    }
}
