package com.kencorp.gads2020leaderboard.Activities.Main;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.chip.Chip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.transition.Explode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.kencorp.gads2020leaderboard.Activities.Main.ui.main.SectionsPagerAdapter;
import com.kencorp.gads2020leaderboard.Activities.SplashScreen;
import com.kencorp.gads2020leaderboard.Activities.Submit.submitActivity;
import com.kencorp.gads2020leaderboard.Models.Learner;
import com.kencorp.gads2020leaderboard.R;
import com.kencorp.gads2020leaderboard.WebServices.LeaderService;
import com.kencorp.gads2020leaderboard.WebServices.ServiceBuilder;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private  LeaderService leaderService;

    int cores = Runtime.getRuntime().availableProcessors();

    private ExecutorService executorService = Executors.newFixedThreadPool(cores);


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);




        Chip submit = (Chip) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Apply activity transition

                    //set an exit transition
                    getWindow().setExitTransition(new Explode());

                    Intent intent = new Intent(getApplicationContext(),submitActivity.class);
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());

                } else {
                    // Swap without transition

                    Intent intent = new Intent(MainActivity.this, submitActivity.class);
                    // trigger that explicit intent with an animation
                    startActivity(intent);

                }
            }
        });


    }

}