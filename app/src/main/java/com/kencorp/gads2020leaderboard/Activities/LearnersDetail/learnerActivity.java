package com.kencorp.gads2020leaderboard.Activities.LearnersDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;

import android.os.Bundle;

import com.kencorp.gads2020leaderboard.Activities.LearnersDetail.ui.learner.LearnerFragment;
import com.kencorp.gads2020leaderboard.Activities.Main.ui.main.LearningAdapter.LearningRecyclerAdapter;
import com.kencorp.gads2020leaderboard.Activities.Submit.ui.submit.SubmitFragment;
import com.kencorp.gads2020leaderboard.R;

public class learnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learner_activity);
       Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, LearnerFragment.newInstance())
                    .commitNow();
        }



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

}
