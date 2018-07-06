package com.beatrice.nearby_nearme_gym.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserModel {

    @SerializedName("data")
    private List<User_profile>userlist;

    public UserModel(List<User_profile> userlist) {
        this.userlist = userlist;
    }

    public List<User_profile> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<User_profile> userlist) {
        this.userlist = userlist;
    }
}
