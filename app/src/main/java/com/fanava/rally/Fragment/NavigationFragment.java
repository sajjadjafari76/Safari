package com.fanava.rally.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.fanava.rally.Activity.InfoTournament;
import com.fanava.rally.R;


public class NavigationFragment extends Fragment {

    private AlertDialog dialog;
    private AlertDialog.Builder builder;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navigation, container, false);
        Button button = view.findViewById(R.id.button);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.dialog_rule, null);

                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setView(alertLayout);
                alert.setCancelable(false);
                AlertDialog dialog = alert.create();
                dialog.show();
                
                Button btn = alertLayout.findViewById(R.id.button);
                final CheckBox checkBox = alertLayout.findViewById(R.id.checkbox);
                TextView textView = alertLayout.findViewById(R.id.content);
                
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkBox.isChecked()){
                            
                        }else {
                            Toast.makeText(getContext(), "با قوانین موافق نیستید !!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        return view;
    }

}
