package com.kencorp.gads2020leaderboard.Activities.Main.ui.main.SkillAdapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kencorp.gads2020leaderboard.Models.Learner;
import com.kencorp.gads2020leaderboard.R;

public class SkillViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView textViewName;
    private TextView textViewInfo;

    private Intent intent ;

    private Activity activity ;

    private int learnerId;



    private SkillRecyclerAdapter.BoardItem boardItem = new SkillRecyclerAdapter.BoardItem() {
        @Override
        public void onClick() {


            // intent.setClass(itemView.getContext().getApplicationContext(), learnerActivity.class);

            intent.putExtra("learnerId",learnerId);
            itemView.getContext().startActivity(intent);

//            activity.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

        }
    };


    public SkillViewHolder(@NonNull View itemView) {
        super(itemView);

        textViewName = itemView.findViewById(R.id.name_learner);
        textViewInfo = itemView.findViewById(R.id.info);
    }

    public void bindTo(Learner learner){

        this.learnerId = learner.getId();

        textViewName.setText(learner.getName());
        String text =learner.getScore()+" "+itemView.getContext().getString(R.string.skillMsg)+" "+learner.getCountry();
        textViewInfo.setText(text);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boardItem.onClick();

            }
        });
    }

    public void clear(){

        itemView.invalidate();
    }

    public void setExtras(Activity activity,Intent intent){
        this.activity=activity;
        this.intent = intent ;
    }

}
