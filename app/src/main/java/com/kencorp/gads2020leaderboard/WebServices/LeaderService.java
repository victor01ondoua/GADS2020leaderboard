package com.kencorp.gads2020leaderboard.WebServices;

import com.kencorp.gads2020leaderboard.Models.Learner;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;




public interface LeaderService {


    //@Headers("x-device-type: Android")
    // Call<List<Idea>> getIdeas(@Header("Accept-Language") String language);


    // @GET("ideas")
    // Call<List<Idea>> getIdeas();

    @GET("/api/hours")
        //Call<List<Idea>> getIdeas(@Query("owner")String owner);
    //Call<List<Learner>> getLearnersByHour(@QueryMap HashMap<String,String> filters);

    Call<List<Learner>> getLearnersByHour();


    @GET("/api/skilliq")
        //Call<List<Idea>> getIdeas(@Query("owner")String owner);
    //Call<List<Learner>> getLearnersBySkill(@QueryMap HashMap<String,String> filters);
    Call<List<Learner>> getLearnersBySkill();


   // @GET("learners/{id}")
   // Call<Learner> getLearner(@Path("id")int id);


    @POST("https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    Call<Void> submitProject(
                                @Field("entry.1877115667") String firstname,
                                @Field("entry.2006916086") String lastname,
                                @Field("entry.284483984") String link,
                                @Field("entry.1824927963") String email
    );

    @FormUrlEncoded
    @PUT("https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    Call<Void>  updateLearner(
            @Field("entry.1877115667") String firstname,
            @Field("entry.2006916086") String lastname,
            @Field("entry.284483984") String link,
            @Field("entry.1824927963") String email
    );



    @DELETE("learners/{id}")
    Call<Void> deleteIdea(@Path("id") int id);


}
