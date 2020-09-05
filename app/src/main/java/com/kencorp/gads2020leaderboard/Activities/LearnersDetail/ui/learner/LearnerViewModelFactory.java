package com.kencorp.gads2020leaderboard.Activities.LearnersDetail.ui.learner;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.kencorp.gads2020leaderboard.Repository.Repository;

import javax.inject.Inject;

public class LearnerViewModelFactory implements ViewModelProvider.Factory {


    private Repository repo;

    @Inject
    public LearnerViewModelFactory(Repository repo) {
        this.repo = repo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if(modelClass.isAssignableFrom(LearnerViewModel.class)){

            return (T) new LearnerViewModel(this.repo);

        }else{
            throw new IllegalArgumentException("Unknown argument in LearnerViewModelFactory");
        }

     }
}
