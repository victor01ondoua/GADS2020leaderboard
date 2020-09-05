package com.kencorp.gads2020leaderboard.Activities.LearnersDetail.ui.learner;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.kencorp.gads2020leaderboard.Activities.Main.ui.main.LearningAdapter.LearningRecyclerAdapter;
import com.kencorp.gads2020leaderboard.Activities.MainApplication;
import com.kencorp.gads2020leaderboard.Models.Learner;
import com.kencorp.gads2020leaderboard.R;
import com.kencorp.gads2020leaderboard.dagger.MainComponent;
import com.kencorp.gads2020leaderboard.utils.CircleImage;

import javax.inject.Inject;

public class LearnerFragment extends Fragment implements LearningRecyclerAdapter.boardItem {

    private LearnerViewModel mViewModel;
    private TextView firstname;
    private TextView lastname;
    private TextView score;
    private TextView hours;
    private TextView country;
    private MainComponent component;
    private ImageView image;

   private static int learnerId;

   @Inject
   LearnerViewModelFactory factory;
    private MainComponent component1;


    public static LearnerFragment newInstance(int Id) {
        learnerId = Id;

        return new LearnerFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        component1 = ((MainApplication)getActivity().getApplication()).getComponent();
        component1.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.learner_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.mipmap.toplearner);

        mViewModel = ViewModelProviders.of(this,factory).get(LearnerViewModel.class);


        //image = (ImageView) getActivity().findViewById(R.id.image);

       // firstname = (TextView) getActivity().findViewById(R.id.firstname);
        lastname = (TextView) getActivity().findViewById(R.id.lastname);
        score = (TextView) getActivity().findViewById(R.id.score);
        hours = (TextView)getActivity().findViewById(R.id.hours);
        country = (TextView)getActivity().findViewById(R.id.country);

        //image.setImageBitmap(bm);
        //image.setMaxHeight(50);
        //image.setMaxWidth(50);
        //image.animate();

        mViewModel.getLearner(learnerId).observe(getViewLifecycleOwner(), new Observer<Learner>() {
            @Override
            public void onChanged(Learner learner) {

                Log.v("LearnerFragment"," "+learner);

              //firstname.setText(learner.getName());
                lastname.setText(learner.getName());
                score.setText(String.valueOf(learner.getScore()));
                hours.setText(String.valueOf(learner.getHours()));
                country.setText(learner.getCountry());
            }
        });

        // TODO: Use the ViewModel
    }

    @Override
    public void onClick() {

    }


}
