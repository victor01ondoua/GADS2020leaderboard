package com.kencorp.gads2020leaderboard.Activities.Main.ui.main.LearningAdapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;

import com.kencorp.gads2020leaderboard.Models.Learner;
import com.kencorp.gads2020leaderboard.R;


public class LearningRecyclerAdapter extends PagedListAdapter<Learner,LearningViewHolder> {



    public interface boardItem{
        void onClick();
    }

    private Learner learner;

    private Activity activity;
    private Intent intent;

    public LearningRecyclerAdapter() {

        super(diffCallback);
    }


    protected LearningRecyclerAdapter(@NonNull  DiffUtil.ItemCallback<Learner> diffCallback) {

        super(diffCallback);
    }


    public void setActivity(Activity activity){
        this.activity = activity ;
    }

    public void setIntent(Intent intent){
        this.intent = intent;
    }



    @NonNull
    @Override
    public LearningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_learning,parent,false);

        return new LearningViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LearningViewHolder holder, int position) {


        learner = getItem(position);
        if(learner!=null){
            holder.bindTo(learner);
            holder.setExtras(this.activity,this.intent);
        }else{
            holder.clear();
        }

    }


   private static DiffUtil.ItemCallback<Learner> diffCallback = new DiffUtil.ItemCallback<Learner>() {
        @Override
        public boolean areItemsTheSame(@NonNull Learner oldItem, @NonNull Learner newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Learner oldItem, @NonNull Learner newItem) {
            return oldItem.equals(newItem);
        }
    };

}
