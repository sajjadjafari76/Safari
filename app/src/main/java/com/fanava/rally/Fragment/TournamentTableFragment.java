package com.fanava.rally.Fragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fanava.rally.Activity.InfoTournament;
import com.fanava.rally.Interface.OnclickMore;
import com.fanava.rally.Model.ClassModel;
import com.fanava.rally.R;
import com.fanava.rally.Utils.RecyclerItemClickListener;
import com.fanava.rally.adapter.RecyclerMainAdapter;
import static com.fanava.rally.MainActivity.main;


public class TournamentTableFragment extends Fragment implements OnclickMore {

    RecyclerView recyclerView;
    RecyclerMainAdapter adapter;
    ClassModel classModel;
    FragmentTransaction transaction;
    private FragmentActivity myContext;


    public TournamentTableFragment(ClassModel classModel) {
        this.classModel = classModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        main = true;

        View view = inflater.inflate(R.layout.fragment_tournament_table, container, false);
        transaction = myContext.getSupportFragmentManager().beginTransaction();
        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new RecyclerMainAdapter(getContext(), true, classModel, TournamentTableFragment.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);

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

    @Override
    public void onclick(int more) {

        switch (more) {
            case 0:
                transaction.replace(R.id.main_container, new MoreFragment(0)).addToBackStack(null);
                transaction.commit();
                break;
            case 1:
                transaction.replace(R.id.main_container, new MoreFragment(1)).addToBackStack(null);
                transaction.commit();
                break;
            case 2:
                transaction.replace(R.id.main_container, new MoreFragment(2)).addToBackStack(null);
                transaction.commit();
                break;
        }
    }
//
}
