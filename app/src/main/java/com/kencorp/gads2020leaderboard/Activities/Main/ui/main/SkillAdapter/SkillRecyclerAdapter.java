package com.kencorp.gads2020leaderboard.Activities.Main.ui.main.SkillAdapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.kencorp.gads2020leaderboard.Models.Learner;
import com.kencorp.gads2020leaderboard.R;

public class SkillRecyclerAdapter extends PagedListAdapter<Learner,SkillViewHolder> {


    private Learner learner;


    private Activity activity;
    private Intent intent;


    public interface BoardItem{
        void onClick();
    }

    public SkillRecyclerAdapter() {
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
    public SkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_skill,parent,false);
        return new SkillViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillViewHolder holder, int position) {

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
