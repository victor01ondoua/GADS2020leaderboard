package com.kencorp.gads2020leaderboard.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.kencorp.gads2020leaderboard.Activities.Main.MainActivity;
import com.kencorp.gads2020leaderboard.R;

public class SplashScreen extends AppCompatActivity {


    private static final long ANIM_DURATION = 2500;
    private ImageView appLogo;
    private TextView appName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // init variable
        appLogo = findViewById(R.id.appLogo);
        // remove visibility on widget
        appLogo.setVisibility(View.GONE);



        // create an handler which will launch a LoginScreen Activity after a 3 second
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //create an explicit intent which will trigger LoginScreen

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Apply activity transition


                    //set an exit transition

                    getWindow().setEnterTransition(new Fade(Fade.IN));
                   // getWindow().setEnterTransition(new ChangeBounds());

                   // getWindow().setEnterTransition(new AutoTransition());

                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    // trigger that explicit intent with an animation
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this).toBundle());

                    finishAfterTransition();
                } else {
                    // Swap without transition

                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    // trigger that explicit intent with an animation
                    startActivity(intent);

                    finish();
                }



            }
        },3500);

        // start animation
        launchInitialAnimation();



    }

    private void launchInitialAnimation() {

        ObjectAnimator logoFadeInAnimator = ObjectAnimator.ofFloat(appLogo, "alpha", 0.0f, 1.0f);
        logoFadeInAnimator.setDuration(ANIM_DURATION)
                .setInterpolator(new AccelerateDecelerateInterpolator());

        // This anim is used for moving the logo downwards
        ObjectAnimator logoDownwardMovementAnimator = ObjectAnimator.ofFloat(appLogo, "translationY", 0f, 100f);
        logoDownwardMovementAnimator.setDuration(ANIM_DURATION)
                .setInterpolator(new AccelerateDecelerateInterpolator());




        AnimatorSet set = new AnimatorSet();
        set.playTogether(logoFadeInAnimator, logoDownwardMovementAnimator);
        set.start();


        // make widget visible
        appLogo.setVisibility(View.VISIBLE);

    }





}