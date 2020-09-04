package com.kencorp.gads2020leaderboard.Activities.Main.ui.main.SkillAdapter;

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

    public SkillViewHolder(@NonNull View itemView) {
        super(itemView);

        textViewName = itemView.findViewById(R.id.name_learner);
        textViewInfo = itemView.findViewById(R.id.info);
    }

    public void bindTo(Learner learner){

        textViewName.setText(learner.getName());
        String text =learner.getScore()+" "+itemView.getContext().getString(R.string.skillMsg)+" "+learner.getCountry();
        textViewInfo.setText(text);

    }

    public void clear(){

        itemView.invalidate();
    }
}
