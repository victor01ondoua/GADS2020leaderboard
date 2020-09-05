package com.kencorp.gads2020leaderboard.Activities.Main.ui.main.LearningAdapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kencorp.gads2020leaderboard.Activities.LearnersDetail.learnerActivity;
import com.kencorp.gads2020leaderboard.Activities.Main.ui.main.LearningAdapter.LearningRecyclerAdapter.boardItem;
import com.kencorp.gads2020leaderboard.Models.Learner;
import com.kencorp.gads2020leaderboard.R;

public class LearningViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView textViewName;
    private TextView textViewInfo;

    private Intent intent ;

    private Activity activity ;

    private int learnerId;



    private boardItem  boardItem = new boardItem() {
        @Override
        public void onClick() {


           // intent.setClass(itemView.getContext().getApplicationContext(), learnerActivity.class);

            intent.putExtra("learnerId",learnerId);
            itemView.getContext().startActivity(intent);



            activity.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

        }
    };



    public LearningViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewName = itemView.findViewById(R.id.name_learner);
        textViewInfo = itemView.findViewById(R.id.info);
    }

    public void bindTo(Learner learner){

        this.learnerId = learner.getId();
        textViewName.setText(learner.getName());
        String text =   learner.getHours()+" "+itemView.getContext().getString(R.string.learningmsg)+" " +learner.getCountry();
        textViewInfo.setText(text);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boardItem.onClick();

            }
        });


    }

    public int getLearnerId(){
        return this.learnerId;
    }

    public void setExtras(Activity activity,Intent intent){
        this.activity=activity;
        this.intent = intent ;
    }

    public void clear(){

        itemView.invalidate();
    }

/*
    @Override
    public void onClick(View v) {

        boardItem.onClick();

    }*/
}
