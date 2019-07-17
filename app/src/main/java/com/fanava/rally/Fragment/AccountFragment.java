package com.fanava.rally.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.fanava.rally.Activity.AddCompanionActivity;
import com.fanava.rally.Activity.AddNavigatActivity;
import com.fanava.rally.Activity.AddVehicleActivity;
import com.fanava.rally.Activity.AllRecords;
import com.fanava.rally.Activity.ChangePasswordActivity;
import com.fanava.rally.Activity.EditProfileActivity;
import com.fanava.rally.Activity.SendFeedback;
import com.fanava.rally.Activity.TournamentRecords;
import com.fanava.rally.R;

public class AccountFragment extends Fragment {

    private RelativeLayout tournament_records, all_records, send_feedback, change_password, edit_profile,add_vehicle,add_companion,add_navigator;
    FragmentManager fm = getFragmentManager();

    FragmentTransaction transaction;
    private FragmentActivity myContext;

//    FragmentTransaction transaction;
//    private FragmentActivity myContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        transaction = myContext.getSupportFragmentManager().beginTransaction();
        tournament_records = view.findViewById(R.id.tournament_records);
        all_records = view.findViewById(R.id.all_records);
        send_feedback = view.findViewById(R.id.send_feedback);
        change_password = view.findViewById(R.id.change_password);
        edit_profile = view.findViewById(R.id.edit_profile);
        add_navigator = view.findViewById(R.id.add_navigator);
        add_companion = view.findViewById(R.id.add_companion);
        add_vehicle = view.findViewById(R.id.add_vehicle);

        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), ChangePasswordActivity.class));
            }
        });
        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), EditProfileActivity.class));
            }
        });
        tournament_records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), TournamentRecords.class));
            }
        });
        all_records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), AllRecords.class));
            }
        });
        send_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), SendFeedback.class));
            }
        });

        add_vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), AddVehicleActivity.class));
            }
        });

        add_navigator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), AddNavigatActivity.class));
            }
        });

        add_companion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), AddCompanionActivity.class));
            }
        });
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


}

