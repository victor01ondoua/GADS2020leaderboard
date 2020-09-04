package com.kencorp.gads2020leaderboard.Activities.LearnersDetail.ui.learner;

import androidx.lifecycle.ViewModelProviders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.kencorp.gads2020leaderboard.Activities.Main.ui.main.LearningAdapter.LearningRecyclerAdapter;
import com.kencorp.gads2020leaderboard.R;
import com.kencorp.gads2020leaderboard.dagger.MainComponent;
import com.kencorp.gads2020leaderboard.utils.CircleImage;

public class LearnerFragment extends Fragment implements LearningRecyclerAdapter.boardItem {

    private LearnerViewModel mViewModel;
    private TextInputEditText firstname;
    private TextInputEditText lastname;
    private TextInputEditText score;
    private TextInputEditText hours;
    private TextInputEditText country;
    private MainComponent component;
    private ImageView image;


    public static LearnerFragment newInstance() {
        return new LearnerFragment();
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

        mViewModel = ViewModelProviders.of(this).get(LearnerViewModel.class);


        image = (ImageView) getActivity().findViewById(R.id.image);

        firstname = (TextInputEditText) getActivity().findViewById(R.id.firstname);
        lastname = (TextInputEditText) getActivity().findViewById(R.id.lastname);
        score = (TextInputEditText) getActivity().findViewById(R.id.score);
        hours = (TextInputEditText)getActivity().findViewById(R.id.hours);
        country = (TextInputEditText)getActivity().findViewById(R.id.country);

        image.setImageBitmap(bm);
        image.setMaxHeight(50);
        image.setMaxWidth(50);

        image.animate();





        // TODO: Use the ViewModel
    }

    @Override
    public void onClick() {

    }
}
