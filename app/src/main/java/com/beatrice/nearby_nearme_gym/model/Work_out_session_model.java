package com.beatrice.nearby_nearme_gym.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class Work_out_session_model {
    @SerializedName("email")
    @Expose
    public String email;

    @SerializedName("date")
    @Expose
    public String date;


    @SerializedName("exercise_type_name")
    @Expose
    public String exercise_type_name;

    @SerializedName("no_of_sets")
    @Expose
    public String  no_of_sets;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExercise_type_name() {
        return exercise_type_name;
    }

    public void setExercise_type_name(String exercise_type_name) {
        this.exercise_type_name = exercise_type_name;
    }

    public String getNo_of_sets() {
        return no_of_sets;
    }

    public void setNo_of_sets(String no_of_sets) {
        this.no_of_sets = no_of_sets;
    }
}
