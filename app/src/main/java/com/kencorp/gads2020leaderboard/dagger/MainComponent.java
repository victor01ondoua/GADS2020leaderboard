package com.kencorp.gads2020leaderboard.dagger;

import com.kencorp.gads2020leaderboard.Activities.LearnersDetail.ui.learner.LearnerFragment;
import com.kencorp.gads2020leaderboard.Activities.Main.MainActivity;
import com.kencorp.gads2020leaderboard.Activities.Main.ui.main.PlaceholderFragment;
import com.kencorp.gads2020leaderboard.Activities.Submit.ui.submit.SubmitFragment;

import dagger.Component;

@Component(modules = {repositoryModule.class})
public interface MainComponent {

    void inject(PlaceholderFragment fragment);
    void inject(SubmitFragment fragment);
    void inject(LearnerFragment fragment);

}
