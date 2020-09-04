package com.kencorp.gads2020leaderboard.Activities.Submit.ui.submit;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.kencorp.gads2020leaderboard.Repository.Repository;

import javax.inject.Inject;

public class SubmitViewModelFactory implements ViewModelProvider.Factory {

    private Repository repository;

    @Inject
    public SubmitViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if(modelClass.isAssignableFrom(SubmitViewModel.class)){

            return (T) new SubmitViewModel(this.repository);
        }else{
            throw new IllegalArgumentException("unknown parameter in SubmitViewModelFactory create");
        }


    }
}
