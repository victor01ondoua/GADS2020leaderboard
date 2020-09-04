package com.kencorp.gads2020leaderboard.Activities.Main.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.kencorp.gads2020leaderboard.Activities.Submit.ui.submit.SubmitViewModel;
import com.kencorp.gads2020leaderboard.Repository.Repository;

import javax.inject.Inject;

public class PageViewModelFactory implements ViewModelProvider.Factory {


    private Repository repository;


    public PageViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if(modelClass.isAssignableFrom(PageViewModel.class)){


            return (T) new PageViewModel(this.repository);
        }/*else if(modelClass.isAssignableFrom(SubmitViewModel.class)) {

            return (T) new SubmitViewModel(this.repository);

        }*/else{
            throw new IllegalArgumentException("unknown argument");
        }


       // return null;
    }
}
