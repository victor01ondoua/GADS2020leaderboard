package com.kencorp.gads2020leaderboard.Activities.LearnersDetail.ui.learner;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.kencorp.gads2020leaderboard.Models.Learner;
import com.kencorp.gads2020leaderboard.Repository.Repository;

public class LearnerViewModel extends ViewModel {


    private Repository repository ;

    public LearnerViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<Learner> getLearner(int Id){

        return this.repository.getLearner(Id);
    }
}
