package com.kencorp.gads2020leaderboard.Repository;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.kencorp.gads2020leaderboard.Dao.leaderBoardDao;
import com.kencorp.gads2020leaderboard.Models.Learner;
import com.kencorp.gads2020leaderboard.WebServices.LeaderService;
import com.kencorp.gads2020leaderboard.WebServices.ServiceBuilder;
import com.kencorp.gads2020leaderboard.database.LeaderBoardDb;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class Repository {

    private final LeaderService leaderService;
    private Application application;


    int cores = Runtime.getRuntime().availableProcessors();

    private ExecutorService executorService = Executors.newFixedThreadPool(cores);

    private leaderBoardDao dao ;
    private Integer n;

    private MutableLiveData<Boolean> submit  = new MutableLiveData<>();


    @Inject
    public Repository(Application application) {


        Log.v("Repository",application.getPackageName());

        LeaderBoardDb db = LeaderBoardDb.getInstance(application);

        dao = db.leaderBoardDao();
        leaderService = new ServiceBuilder().builderService(LeaderService.class);

        this.application = application ;
    }



    public void getData()
    {

        Log.v("Main", " getData");
        final Call<List<Learner>> skillRequest = leaderService.getLearnersBySkill();


        skillRequest.enqueue(new Callback<List<Learner>>() {
            @Override
            public void onResponse(Call<List<Learner>> call, Response<List<Learner>> response) {

                if(response.isSuccessful()) {
                    Toast.makeText(application.getApplicationContext(),"Data is loading.....",Toast.LENGTH_LONG).show();


                    for(Learner learner: response.body()){

                        Log.v("Main", ""+learner);

                   //     Toast.makeText(application.getApplicationContext(),"Data is loading.....",Toast.LENGTH_LONG).show();

                        executorService.execute(new Runnable() {
                            @Override
                            public void run() {

                                dao.insertLearner(learner);
                            }
                        });
                    }
                }else if(response.code() == 401){

                    Toast.makeText(application.getApplicationContext(),"Your session has timeout",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(application.getApplicationContext(),"Failed to retrieve items",Toast.LENGTH_LONG).show();
                }



            }

            @Override
            public void onFailure(Call<List<Learner>> call, Throwable t) {

                Log.v("Main", " failure");

                if(t instanceof Exception){
                    Toast.makeText(application.getApplicationContext(),"a connection error occured",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(application.getApplicationContext(),"Failed to retrieve ideas",Toast.LENGTH_LONG).show();
                }

            }


        } );


        final Call<List<Learner>> hourRequest = leaderService.getLearnersByHour();


        hourRequest.enqueue(new Callback<List<Learner>>() {
            @Override
            public void onResponse(Call<List<Learner>> call, Response<List<Learner>> response) {

                if(response.isSuccessful()) {
                    Toast.makeText(application.getApplicationContext(),"Data is loading.....",Toast.LENGTH_LONG).show();


                    for(Learner learner: response.body()){

                        Log.v("Main", ""+learner);

                        Toast.makeText(application.getApplicationContext(),"Data is loading.....",Toast.LENGTH_LONG).show();

                        executorService.execute(new Runnable() {
                            @Override
                            public void run() {

                                dao.insertLearner(learner);
                            }
                        });
                    }
                }else if(response.code() == 401){

                    Toast.makeText(application.getApplicationContext(),"Your session has timeout",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(application.getApplicationContext(),"Failed to retrieve items",Toast.LENGTH_LONG).show();
                }

            }


            @Override
            public void onFailure(Call<List<Learner>> call, Throwable t) {

                Log.v("Main", " failure");
                if(t instanceof Exception){
                    Toast.makeText(application.getApplicationContext(),"a connection error occured",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(application.getApplicationContext(),"Failed to retrieve ideas",Toast.LENGTH_LONG).show();
                }

            }


        } );


    }

    public LiveData<PagedList<Learner>> getLearners(){

        executorService.execute(new Runnable() {
            @Override
            public void run() {

                n = dao.getNumber().getValue();
                if(n==null){

                    getData();
                }

            }
        });

        return new LivePagedListBuilder<>(dao.getBoardListTopLearners(),50).build();
    }


    public LiveData<PagedList<Learner>> getTopSkill(){

        executorService.execute(new Runnable() {
            @Override
            public void run() {

                n = dao.getNumber().getValue();
                if( n==null){

                    getData();
                }
            }
        });

        return new LivePagedListBuilder<>(dao.getBoardListTopSkill(),50).build();
    }


    public LiveData<Boolean> submission(String firstname, String lastname, String email, String link){

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                leaderService.submitProject(firstname,lastname,email,link).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()){

                            submit.setValue(true);
                        }else{
                            submit.setValue(false);
                        }

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });

        return submit;
    }
}
