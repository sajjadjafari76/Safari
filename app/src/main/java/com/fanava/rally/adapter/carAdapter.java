package com.fanava.rally.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanava.rally.Model.carModelView;
import com.fanava.rally.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class carAdapter  extends RecyclerView.Adapter<carAdapter.carViewHolder> {
    List<carModelView> carModelList;
    Context context;
    AdapterListeners.CarListener listener;
    public class carViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView img_car;
        public TextView txt_carName;
        public TextView txt_model;
        public ImageView img_pelak;
        public ImageView img_bimeh;
        public TextView txt_color;
        public ImageButton btn_remove;
        public carViewHolder(View itemView) {
            super(itemView);
            img_car = itemView.findViewById(R.id.img_car);
            txt_carName=itemView.findViewById(R.id.txt_carName);
            txt_model=itemView.findViewById(R.id.txt_model);
            img_pelak=itemView.findViewById(R.id.img_pelak);
            img_bimeh=itemView.findViewById(R.id.img_bimeh);
            txt_color=itemView.findViewById(R.id.txt_color);
            btn_remove=itemView.findViewById(R.id.btn_remove);


        }

    }



    public carAdapter(Context context, List<carModelView> List, AdapterListeners.CarListener listener1) {

        carModelList = List;
        this.context = context;
        listener=listener1;
    }

    @Override
    public carViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_car,viewGroup,false);
        return new carViewHolder(view);

    }



    //onBindViewHolder()
    @Override
    public void onBindViewHolder(final carViewHolder holder, final int position) {

        final carModelView product = carModelList.get(position);

        //  String formattedPrice = new DecimalFormat("###,###.####").format(product.getPrice());
        holder.txt_carName.setText(product.getCarName());
        holder.txt_color.setText(product.getColor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(position);
            }
        });
        holder.btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRemove(position);
            }
        });
        holder.txt_model.setText(product.getModel());
        holder.img_bimeh.setImageURI(product.getCar_bimeh());
        holder.img_pelak.setImageURI(product.getCar_pelak());
        holder.img_car.setImageURI(product.getCar_pic());
      //  holder.img_car.setImageBitmap(String.valueOf(product.getCommentCount()));


    }

    //getItemCount() : returns the mNumberItems var
    @Override
    public int getItemCount() {
        //  mNumberItems =
        return carModelList.size();
    }



}

