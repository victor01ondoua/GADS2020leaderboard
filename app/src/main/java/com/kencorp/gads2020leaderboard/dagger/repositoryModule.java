package com.kencorp.gads2020leaderboard.dagger;

import android.app.Application;

import androidx.lifecycle.ViewModelProvider;

import com.kencorp.gads2020leaderboard.Activities.Main.ui.main.PageViewModelFactory;
import com.kencorp.gads2020leaderboard.Activities.Submit.ui.submit.SubmitViewModelFactory;
import com.kencorp.gads2020leaderboard.R;
import com.kencorp.gads2020leaderboard.Repository.Repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class repositoryModule {


    private final Application application;

    public repositoryModule(Application application) {
        this.application = application;
    }


    @Provides
    Application provideApplication(){

        return application;
    }


    @Provides
    Repository providerepository(Application application){

        return new Repository(application);
    }

    @Provides
    ViewModelProvider.Factory provideViewModelFactory(Repository repository){
        return new PageViewModelFactory(repository);
    }




}
