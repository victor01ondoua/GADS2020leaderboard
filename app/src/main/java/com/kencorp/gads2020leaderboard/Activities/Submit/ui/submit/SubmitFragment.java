package com.kencorp.gads2020leaderboard.Activities.Submit.ui.submit;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.transition.Explode;
import androidx.transition.Fade;
import androidx.transition.Scene;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputEditText;
import com.kencorp.gads2020leaderboard.Activities.MainApplication;
import com.kencorp.gads2020leaderboard.R;
import com.kencorp.gads2020leaderboard.dagger.MainComponent;

import javax.inject.Inject;


public class SubmitFragment extends Fragment {

    private SubmitViewModel mViewModel;
    private Scene scene1;
    private Scene scene2;

    private static final long ANIM_DURATION = 20000;
    private TextInputEditText firstname;
    private TextInputEditText lastname;
    private TextInputEditText email;
    private TextInputEditText link;
    private MainComponent component;

    public static SubmitFragment newInstance() {
        return new SubmitFragment();
    }

    private AlertDialog alertDialog;

    @Inject
//    ViewModelProvider.Factory factory;
    SubmitViewModelFactory factory;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {



        //Create the scenes
        //scene1 = Scene.getSceneForLayout(container,R.layout.submit_fragment,container.getContext());
        //scene2 = Scene.getSceneForLayout(container,R.layout.validation,container.getContext());


        return inflater.inflate(R.layout.submit_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        component = ((MainApplication)getContext().getApplicationContext()).getComponent();

        component.inject(this);

        mViewModel = ViewModelProviders.of(this,factory).get(SubmitViewModel.class);

        //Transition transition = new Explode();


        firstname = (TextInputEditText) getActivity().findViewById(R.id.firstname);
        lastname = (TextInputEditText) getActivity().findViewById(R.id.lastname);
        email = (TextInputEditText) getActivity().findViewById(R.id.email);
        link = (TextInputEditText)getActivity().findViewById(R.id.link);


        Chip submit = (Chip) getActivity().findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubmissionAlert();
          //      TransitionManager.go(scene1,transition);

            }
        });



    }



    public void AlertResult(int resid,ViewGroup viewGroup){


        //Create the scenes
    //    scene2 = Scene.getSceneForLayout(viewGroup,resid,getContext());

        // create an AlertDialog builder
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        //dialog.setCancelable(false);

        //init a layout inflater
        LayoutInflater inflater = LayoutInflater.from(getContext());
        //set a customized view for the display
        View layoutView = inflater.inflate(resid,null);
        dialog.setView(layoutView);

        // init variable from customView and active them

        //dialog.setTitle(getString(R.string.textSign));

        // create an AlertDialog and display it

        final AlertDialog  alertDialog1 = dialog.create();
        Transition fade = new Fade();

        //TransitionManager.go(scene2,fade);
        //alertDialog1.show();

        animation(alertDialog1,"X",30000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                alertDialog1.hide();
            }
        },3000);
    }



    public void SubmissionAlert(){

        // create an AlertDialog builder
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        //dialog.setCancelable(false);

        //init a layout inflater
        LayoutInflater inflater = LayoutInflater.from(getContext());
        //set a customized view for the display
        View layoutView = inflater.inflate(R.layout.validation,null);
        ViewGroup viewGroup = layoutView.findViewById(R.id.validation);
        dialog.setView(layoutView);


        //Create the scenes
//        scene1 = Scene.getSceneForLayout(viewGroup,R.layout.validation,getContext());

        // init variable from customView and active them

        Chip ok = (Chip) layoutView.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // submit form
                //SuccessAlert();

                alertDialog.hide();
               String firstName = firstname.getText().toString();
               String lastName = lastname.getText().toString();
               String Email  = email.getText().toString();
               String Link   = link.getText().toString();

                mViewModel.getResponse(firstName,lastName,Email,Link).observe(getActivity(),new Observer<Boolean>(){
                    @Override
                    public void onChanged(Boolean aBoolean) {

                        if(aBoolean){

                            AlertResult(R.layout.success_alert,viewGroup);

                        }else{

                            AlertResult(R.layout.failed_alert,viewGroup);

                        }
                    }

                });

            }
        });

        ImageView cancel = (ImageView) layoutView.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // hidden alert
                alertDialog.hide();
            }
        });

        //dialog.setTitle(getString(R.string.textSign));

        // create an AlertDialog and display it


        alertDialog = dialog.create();
        animation(alertDialog,"Y",25000);
        //alertDialog.show();


    }




    private void animation(AlertDialog dialog,String typeTranslation,int duration) {

        if (typeTranslation!=null) {
            if(typeTranslation == "Y"){
                ObjectAnimator logoFadeInAnimator = ObjectAnimator.ofFloat(dialog, getContext().getString(R.string.alphaTransition), 0.0f, 1.0f);
                logoFadeInAnimator.setDuration(duration)
                        .setInterpolator(new AccelerateDecelerateInterpolator());

                // This anim is used for moving the logo downwards
                ObjectAnimator logoDownwardMovementAnimator = ObjectAnimator.ofFloat(dialog, getContext().getString(R.string.translationYTransition), 0f, 100f);
                logoDownwardMovementAnimator.setDuration(duration)
                        .setInterpolator(new AccelerateDecelerateInterpolator());


                AnimatorSet set = new AnimatorSet();
                set.playTogether(logoFadeInAnimator, logoDownwardMovementAnimator);
                set.start();

            }else{

                ObjectAnimator appNameFadeInAnimator = ObjectAnimator.ofFloat(dialog, getContext().getString(R.string.alphaTransition),0.0f,1.0f);
                appNameFadeInAnimator.setDuration(duration).setInterpolator(new AccelerateDecelerateInterpolator());

                ObjectAnimator appNameMovementAnimator = ObjectAnimator.ofFloat(dialog, getContext().getString(R.string.translationXTransition), 200f, 0f);
                appNameMovementAnimator.setDuration(duration)
                        .setInterpolator(new AccelerateDecelerateInterpolator());



                AnimatorSet set = new AnimatorSet();
                set.playTogether(appNameFadeInAnimator, appNameMovementAnimator);
                set.start();

            }


            dialog.show();
        }

        // make widget visible
        //appLogo.setVisibility(View.VISIBLE);

    }


/*
    public void SuccessAlert(){


        // create an AlertDialog builder
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        //dialog.setCancelable(false);

        //init a layout inflater
        LayoutInflater inflater = LayoutInflater.from(getContext());
        //set a customized view for the display
        View layoutView = inflater.inflate(R.layout.success_alert,null);
        dialog.setView(layoutView);

        // init variable from customView and active them


        //dialog.setTitle(getString(R.string.textSign));

        // create an AlertDialog and display it

        final AlertDialog  alertDialog1 = dialog.create();
        alertDialog1.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                alertDialog1.hide();
            }
        },3000);
    }

    public void FailedAlert(){

        // create an AlertDialog builder
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        //dialog.setCancelable(false);

        //init a layout inflater
        LayoutInflater inflater = LayoutInflater.from(getContext());
        //set a customized view for the display
        View layoutView = inflater.inflate(R.layout.failed_alert,null);
        dialog.setView(layoutView);

        // init variable from customView and active them


        //dialog.setTitle(getString(R.string.textSign));

        // create an AlertDialog and display it

       final AlertDialog  alertDialog1 = dialog.create();
       alertDialog1.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               alertDialog1.hide();
            }
        },3000);
    }
*/


}
