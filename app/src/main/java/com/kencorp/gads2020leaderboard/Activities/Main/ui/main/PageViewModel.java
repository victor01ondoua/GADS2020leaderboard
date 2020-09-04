package com.kencorp.gads2020leaderboard.Activities.Main.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.kencorp.gads2020leaderboard.Models.Learner;
import com.kencorp.gads2020leaderboard.Repository.Repository;

import javax.inject.Inject;

public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "Hello world from section: " + input;

        }
    });


    //@Inject
    private Repository repository;



    public PageViewModel(Repository repository) {
        this.repository = repository;
    }

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }


    public LiveData<PagedList<Learner>> getTopLearners(){
        return this.repository.getLearners();
    }

    public LiveData<PagedList<Learner>> getTopSkill(){
        return this.repository.getTopSkill();
    }

}