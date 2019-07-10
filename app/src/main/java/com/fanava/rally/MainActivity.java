package com.fanava.rally;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.fanava.rally.Fragment.AccountFragment;
import com.fanava.rally.Fragment.HomeFragment;
import com.fanava.rally.Fragment.NavigationFragment;
import com.fanava.rally.Fragment.TournamentTableFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import static com.fanava.rally.Fragment.TournamentTableFragment.main;

//import com.fanava.rally.Fragment.TournamentTableFragment
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    final Fragment homeFragment = new HomeFragment();
    final Fragment accountFragment = new AccountFragment();
    final Fragment navigationFragment = new NavigationFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation);
        navigation.setSelectedItemId(R.id.BNV_Home);


        fm.beginTransaction().add(R.id.main_container, accountFragment).hide(accountFragment).commit();
        fm.beginTransaction().add(R.id.main_container, navigationFragment).hide(navigationFragment).commit();
        fm.beginTransaction().add(R.id.main_container, homeFragment).commit();
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.BNV_Home:
                    if (main) {
                        fm.beginTransaction().add(R.id.main_container, accountFragment).hide(accountFragment).commit();
                        fm.beginTransaction().add(R.id.main_container, navigationFragment).hide(navigationFragment).commit();
                        fm.beginTransaction().add(R.id.main_container, homeFragment).show(homeFragment).commit();
                        active = homeFragment;
                        main = false;
                    } else {
                        fm.beginTransaction().hide(active).show(homeFragment).commit();
                        active = homeFragment;
                    }
                    return true;
//
                case R.id.BNV_Map:
                    if (main) {
                        fm.beginTransaction().add(R.id.main_container, accountFragment).hide(accountFragment).commit();
                        fm.beginTransaction().add(R.id.main_container, homeFragment).hide(homeFragment).commit();
                        fm.beginTransaction().add(R.id.main_container, navigationFragment).show(navigationFragment).commit();
                        active = navigationFragment;
                        main = false;
                    } else {
                        fm.beginTransaction().hide(active).show(navigationFragment).commit();
                        active = navigationFragment;
                    }

                    return true;

                case R.id.BNVAccount:

                    if (main) {
                        fm.beginTransaction().add(R.id.main_container, navigationFragment).hide(navigationFragment).commit();
                        fm.beginTransaction().add(R.id.main_container, homeFragment).hide(homeFragment).commit();
                        fm.beginTransaction().add(R.id.main_container, accountFragment).show(accountFragment).commit();
                        active = accountFragment;
                        main = false;
                    } else {
                        fm.beginTransaction().hide(active).show(accountFragment).commit();
                        active = accountFragment;
                    }
                    return true;
            }
            return false;
        }
    };


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            try {
                super.onBackPressed();
            } catch (Exception e) {

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
