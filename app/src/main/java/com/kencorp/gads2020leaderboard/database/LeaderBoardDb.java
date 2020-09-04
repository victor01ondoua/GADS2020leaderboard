package com.kencorp.gads2020leaderboard.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.kencorp.gads2020leaderboard.Dao.leaderBoardDao;
import com.kencorp.gads2020leaderboard.Models.Learner;

@Database(entities = {Learner.class},version = 1,exportSchema = false)
public abstract class LeaderBoardDb extends RoomDatabase {

    private static LeaderBoardDb instance = null;

    public abstract leaderBoardDao leaderBoardDao();

    public static synchronized LeaderBoardDb getInstance(Context context){


        Log.v("Repository",context.getPackageName()+" in");

        if(instance ==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),LeaderBoardDb.class,"leaderboard1.db").build();


            Log.v("Repository",context.getPackageName() +" -" +instance);

        }

        return instance ;
    }
}
