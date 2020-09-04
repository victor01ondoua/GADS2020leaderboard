package com.kencorp.gads2020leaderboard.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "learner",indices = {@Index("Id")})
public class Learner implements Parcelable {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    private  int Id ;

    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name = "hours")
    private int hours;

    @ColumnInfo(name = "country")
    private String country;

    @ColumnInfo(name = "score")
    private int score;


   @Ignore
    public Learner(){

    }

    public Learner(String name, String country, int score, int hours) {
        this.name = name;
        this.country = country;
        this.score = score;
        this.hours = hours;
    }

    protected Learner(Parcel in) {
        Id = in.readInt();
        name = in.readString();
        hours = in.readInt();
        country = in.readString();
        score = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Id);
        dest.writeString(name);
        dest.writeString(country);
        dest.writeInt(score);
        dest.writeInt(hours);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Learner> CREATOR = new Creator<Learner>() {
        @Override
        public Learner createFromParcel(Parcel in) {
            return new Learner(in);
        }

        @Override
        public Learner[] newArray(int size) {
            return new Learner[size];
        }
    };

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }




    @NonNull
    @Override
    public String toString() {

        return "Learner(" +"id=" +Id+ ", name="+name+", hours="+hours+" ,country="+country+" ,score="+score+")";
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {

        return super.equals(obj);
    }



}
