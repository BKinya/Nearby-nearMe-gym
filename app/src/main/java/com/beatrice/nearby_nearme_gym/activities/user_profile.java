package com.beatrice.nearby_nearme_gym.activities;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.beatrice.nearby_nearme_gym.R;
import com.beatrice.nearby_nearme_gym.model.UserModel;
import com.beatrice.nearby_nearme_gym.model.User_profile;
import com.beatrice.nearby_nearme_gym.rest.ApiUtilities;
import com.beatrice.nearby_nearme_gym.rest.UserProfileApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class user_profile extends AppCompatActivity {

    private UserProfileApiService userProfileApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);



        userProfileApiService = ApiUtilities.getUserProfileApi();
        getUserProfile("bitukiki@gmail.com");
    }

    public void getUserProfile(String email) {
        userProfileApiService.getUserProfile(email).enqueue(new Callback<User_profile>() {
            @Override
            public void onResponse(Call<User_profile> call, Response<User_profile> response) {
                if (response.isSuccessful()){

                    User_profile user_profile = response.body();
                    user_profile.setName(response.body().name);
                    user_profile.setEmail(response.body().email);
                    user_profile.setPhone(response.body().phone);
                    user_profile.setGender(response.body().gender);
                    user_profile.setAge(response.body().age);
                    user_profile.setCurrent_weight(response.body().current_weight);
                    user_profile.setTarget_weight(response.body().target_weight);

                    String name = user_profile.getName();
                    String email = user_profile.getEmail();
                    String phone = Integer.toString(user_profile.getPhone());
                    String gender = user_profile.getGender();
                    String age = Integer.toString(user_profile.getAge());
                    String c_weight = Integer.toString(user_profile.getCurrent_weight());
                    String t_weight = Integer.toString(user_profile.getTarget_weight());

                    //array
                    String [] user_p = {name, email, phone, gender, age, c_weight, t_weight};

                    //array adapter
                    ArrayAdapter<String> adapter = new ArrayAdapter<String >(getApplicationContext(), android.R.layout.simple_list_item_1, user_p){
                        @NonNull
                        @Override
                        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                            TextView textView = (TextView) super.getView(position, convertView, parent);
                            textView.setTextColor(Color.parseColor("#323232"));
                            return textView;
                        }
                    };

                    ListView listView = findViewById(R.id.profile_listview);
                    listView.setAdapter(adapter);

                    if (phone == null){
                        Log.i("Sucess", "Null body");
                    }
                    Log.i("Sucess", "my no " +phone);
                }


            }

            @Override
            public void onFailure(Call<User_profile> call, Throwable t) {
                Log.i("Error", t.getMessage());
            }
        });
    }
}
