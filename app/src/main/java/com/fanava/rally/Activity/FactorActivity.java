package com.fanava.rally.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.fanava.rally.Fragment.RuleFragment;
import com.fanava.rally.Fragment.SelectCarFragment;
import com.fanava.rally.R;

public class FactorActivity extends AppCompatActivity {

    FragmentManager fm = getSupportFragmentManager();
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factor);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            bundle.getInt("id", id);
        }

        fm.beginTransaction().replace(R.id.fragment_factor, new SelectCarFragment(id)).commit();

    }
}
