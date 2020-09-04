package com.kencorp.gads2020leaderboard.Dao;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.kencorp.gads2020leaderboard.Models.Learner;

import javax.inject.Inject;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface leaderBoardDao {

    @Query("Select * from learner order by hours DESC")
    DataSource.Factory<Integer,Learner> getBoardListTopLearners();



    @Query("Select * from learner order by score DESC")
    DataSource.Factory<Integer,Learner> getBoardListTopSkill();

    @Query("Select count(*) from learner")
    LiveData<Integer> getNumber();

    @Insert(onConflict = REPLACE)
    void insertLearner(Learner... learner);

   // @Update
  //  void updateLearner();
}
