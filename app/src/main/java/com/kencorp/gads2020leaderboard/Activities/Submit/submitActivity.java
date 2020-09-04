package com.kencorp.gads2020leaderboard.Activities.Submit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.transition.Scene;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.kencorp.gads2020leaderboard.Activities.Submit.ui.submit.SubmitFragment;
import com.kencorp.gads2020leaderboard.R;

public class submitActivity extends AppCompatActivity {

    private ViewGroup sceneRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SubmitFragment.newInstance())
                    .commitNow();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
