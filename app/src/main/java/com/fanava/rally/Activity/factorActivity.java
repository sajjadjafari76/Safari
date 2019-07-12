package com.fanava.rally.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.fanava.rally.Fragment.RuleFragment;
import com.fanava.rally.R;

public class factorActivity extends AppCompatActivity {

    FragmentManager fm = getSupportFragmentManager();

    RuleFragment ruleFragment = new RuleFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factor);

        fm.beginTransaction().add(R.id.fragment_factor, ruleFragment).commit();

    }
}
