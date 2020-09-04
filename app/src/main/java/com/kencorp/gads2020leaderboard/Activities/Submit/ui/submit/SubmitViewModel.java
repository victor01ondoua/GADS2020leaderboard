package com.kencorp.gads2020leaderboard.Activities.Submit.ui.submit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.kencorp.gads2020leaderboard.Repository.Repository;

public class SubmitViewModel extends ViewModel {


    private Repository repository;

    public SubmitViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<Boolean> getResponse(String firstname, String lastname, String email, String link){

        return this.repository.submission(firstname, lastname, email, link);
    }
}
