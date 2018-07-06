package com.beatrice.nearby_nearme_gym.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User_profile {

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("email")
    @Expose
    public String email;

    @SerializedName("phone")
    @Expose
    public int phone;

    @SerializedName("age")
    @Expose
    public int age;

    @SerializedName("gender")
    @Expose
    public String gender;

    @SerializedName("current_weight ")
    @Expose
    public int current_weight;

    @SerializedName("target_weight")
    @Expose
    public int target_weight;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getCurrent_weight() {
        return current_weight;
    }

    public void setCurrent_weight(int current_weight) {
        this.current_weight = current_weight;
    }

    public int getTarget_weight() {
        return target_weight;
    }

    public void setTarget_weight(int target_weight) {
        this.target_weight = target_weight;
    }
}
