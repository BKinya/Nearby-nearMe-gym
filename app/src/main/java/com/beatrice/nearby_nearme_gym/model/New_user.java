package com.beatrice.nearby_nearme_gym.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//pojo class
public class New_user {

    @SerializedName("id")
    @Expose
    private  int id;

    @SerializedName("user_id")
    @Expose
    private int user_id;

    @SerializedName("name")
    @Expose
    private String user_name;

    @SerializedName("email")
    @Expose
    private String user_email;

    @SerializedName("password")
    @Expose
    private String user_password;

    public New_user(String name, String email, String password){
        this.user_name = name;
        this.user_email = email;
        this.user_password = password;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    @Override
    public String toString() {
        return "New_user{"+
                "name =" +user_name+ '\''+
                ", email'" +user_email+ '\''+
                ", user_id=" +user_id+
                        ", id=" + id +
                '}';
    }

}
