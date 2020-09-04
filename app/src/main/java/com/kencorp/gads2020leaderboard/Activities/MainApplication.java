package com.kencorp.gads2020leaderboard.Activities;

import android.app.Application;

import com.kencorp.gads2020leaderboard.dagger.DaggerMainComponent;
import com.kencorp.gads2020leaderboard.dagger.MainComponent;
import com.kencorp.gads2020leaderboard.dagger.repositoryModule;

public class MainApplication extends Application {


    private MainComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerMainComponent.builder()
                .repositoryModule(new repositoryModule(this))
                .build();

    }

    public MainComponent getComponent()
    {
        return this.component;
    }
}
