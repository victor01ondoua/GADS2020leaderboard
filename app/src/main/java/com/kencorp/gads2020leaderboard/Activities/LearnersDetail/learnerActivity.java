package com.kencorp.gads2020leaderboard.Activities.LearnersDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;

import android.content.Intent;
import android.os.Bundle;

import com.kencorp.gads2020leaderboard.Activities.LearnersDetail.ui.learner.LearnerFragment;
import com.kencorp.gads2020leaderboard.Activities.Main.ui.main.LearningAdapter.LearningRecyclerAdapter;
import com.kencorp.gads2020leaderboard.Activities.Submit.ui.submit.SubmitFragment;
import com.kencorp.gads2020leaderboard.R;

public class learnerActivity extends AppCompatActivity {

    private Intent intent;
    private int learnerId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learner_activity);
       Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        intent = getIntent();

        learnerId = intent.getIntExtra("learnerId",0);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, LearnerFragment.newInstance(learnerId))
                    .commitNow();
        }



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

}
