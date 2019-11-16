package com.android.lateralmenuexample;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;

import com.android.lateralmenuexample.ui.home.HomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.OrientationEventListener;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private FloatingActionButton fab_main, fab1_mail, fab2_share, fab_example;
    private Animation fab_open, fab_close, fab_clock, fab_anticlock;
    Boolean isOpen = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_tools, R.id.nav_share, R.id.nav_send).setDrawerLayout(drawer).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);




        ////////////////// Start Multi floating buttons
        fab_main = findViewById(R.id.fab);
        fab1_mail = findViewById(R.id.fab1);
        fab2_share = findViewById(R.id.fab2);
        fab_example = findViewById(R.id.fab3);

        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_clock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_clock);
        fab_anticlock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_anticlock);



        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if (isOpen) {

                fab2_share.startAnimation(fab_close);
                fab1_mail.startAnimation(fab_close);
                fab_example.startAnimation(fab_close);

                fab_main.startAnimation(fab_anticlock);

                fab2_share.setClickable(false);
                fab_example.setClickable(false);
                fab1_mail.setClickable(false);

                isOpen = false;
            } else {

                fab2_share.startAnimation(fab_open);
                fab1_mail.startAnimation(fab_open);
                fab_example.startAnimation(fab_open);

                fab_main.startAnimation(fab_clock);

                fab2_share.setClickable(true);
                fab1_mail.setClickable(true);
                fab1_mail.setClickable(true);

                isOpen = true;
            }
            }
        });


        fab2_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Share", Toast.LENGTH_SHORT).show();

            }
        });

        fab1_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Email", Toast.LENGTH_SHORT).show();

            }
        });

        fab_example.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Example", Toast.LENGTH_SHORT).show();

            }
        });
    //////////////////////// END MULTI FLOATING BUTTONS




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }


}
