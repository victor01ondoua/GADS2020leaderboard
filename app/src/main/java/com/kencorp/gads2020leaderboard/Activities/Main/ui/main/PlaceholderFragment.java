package com.kencorp.gads2020leaderboard.Activities.Main.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kencorp.gads2020leaderboard.Activities.Main.MainActivity;
import com.kencorp.gads2020leaderboard.Activities.Main.ui.main.LearningAdapter.LearningRecyclerAdapter;
import com.kencorp.gads2020leaderboard.Activities.Main.ui.main.LearningAdapter.LearningViewHolder;
import com.kencorp.gads2020leaderboard.Activities.Main.ui.main.SkillAdapter.SkillRecyclerAdapter;
import com.kencorp.gads2020leaderboard.Activities.MainApplication;
import com.kencorp.gads2020leaderboard.R;
import com.kencorp.gads2020leaderboard.dagger.MainComponent;

import com.kencorp.gads2020leaderboard.Activities.LearnersDetail.learnerActivity;

import javax.inject.Inject;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    @Inject
    ViewModelProvider.Factory factory;

    //@Inject
    PageViewModel pageViewModel;
    private LearningRecyclerAdapter learningRecyclerAdapter;

    private MainComponent component;
    private SkillRecyclerAdapter skillRecyclerAdapter;

    private int index = 1;
    private LearningViewHolder learningViewHolder;
    private LearningViewHolder learningViewHolder1;

    private Intent intent = new Intent();


    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        component = ((MainApplication)getContext().getApplicationContext()).getComponent();

        component.inject(this);

        pageViewModel = ViewModelProviders.of(this,factory).get(PageViewModel.class);

        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
            //this.index =index ;
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        final RecyclerView recyclerView = root.findViewById(R.id.recylerlist);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);



        learningRecyclerAdapter = new LearningRecyclerAdapter();

        skillRecyclerAdapter = new SkillRecyclerAdapter();

        pageViewModel.getText().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });




        if(this.index==1){

            recyclerView.setAdapter(learningRecyclerAdapter);
            learningRecyclerAdapter.setActivity(getActivity());
            intent.setClass(getActivity().getApplicationContext(),learnerActivity.class);
            learningRecyclerAdapter.setIntent(intent);

            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
                @Override
                public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {

                    learningViewHolder1 = (LearningViewHolder)  viewHolder;

                    Log.v("onRecyclerView","learningViewHolder1.getLearnerId() :" + learningViewHolder1.getLearnerId());

                    return 0;
                }

                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

                    learningViewHolder1 = (LearningViewHolder)  viewHolder;

                    Log.v("onRecyclerView","learningViewHolder1.getLearnerId() :" + learningViewHolder1.getLearnerId());

                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    learningViewHolder1 = (LearningViewHolder)  viewHolder;

                    Log.v("onRecyclerView","learningViewHolder1.getLearnerId() :" + learningViewHolder1.getLearnerId());

                }
            });

            itemTouchHelper.attachToRecyclerView(recyclerView);


            pageViewModel.getTopLearners().observe(getActivity(),learningRecyclerAdapter::submitList);

        }else {
            recyclerView.setAdapter(skillRecyclerAdapter);
            pageViewModel.getTopSkill().observe(getActivity(),skillRecyclerAdapter::submitList);
        }



        return root;


    }



}