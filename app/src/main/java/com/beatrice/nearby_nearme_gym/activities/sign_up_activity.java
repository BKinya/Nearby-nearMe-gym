package com.beatrice.nearby_nearme_gym.activities;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.beatrice.nearby_nearme_gym.R;
import com.beatrice.nearby_nearme_gym.model.New_user;
import com.beatrice.nearby_nearme_gym.rest.ApiUtilities;
import com.beatrice.nearby_nearme_gym.rest.UserApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;


public class sign_up_activity extends LifecycleLoggingActivity {

    private UserApiService userApiService;

    //api access token
    private static  String API_KEY = "";

    public static final String BASE_URL = "http://nearbynearmegym.herokuapp.com/";

    //views
    private EditText name_edittxt, email_edittxt, password_edittxt, confirm_password_edittxt;
    private Button signup_btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_activity);

        userApiService = ApiUtilities.getUserApiService();

        name_edittxt = findViewById(R.id.name_edit_txt);
        email_edittxt = findViewById(R.id.email_edit_txt);
        password_edittxt = findViewById(R.id.password_edit_txt);
        confirm_password_edittxt = findViewById(R.id.confirm_password_edit_txt);

        signup_btn_signup = findViewById(R.id.signup_btn_signup);

        isUserInputValid();

        signup_btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                New_user_asyncktask new_user_asyncktask = new New_user_asyncktask();
                new_user_asyncktask.execute();
            }
        });

    }


    /**
     *
     * @return
     */

    public boolean isUserInputValid(){
       name_edittxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
           @Override
           public void onFocusChange(View view, boolean b) {
               if (name_edittxt.getText().toString().length() == 0){
                   name_edittxt.setError("Enter Your Name");
                   return;
               }
           }
       });

       email_edittxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
           @Override
           public void onFocusChange(View view, boolean b) {
               if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email_edittxt.getText().toString()).matches()){
                   email_edittxt.setError("Enter Valid Email Address");
                   return;
               }
           }
       });

       password_edittxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
           @Override
           public void onFocusChange(View view, boolean b) {
               if (password_edittxt.getText().toString().length()<8){
                   password_edittxt.setError("Too Short. Required length, at least 8 character");
                   return;
               }
           }
       });

       confirm_password_edittxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
           @Override
           public void onFocusChange(View view, boolean b) {
               if (!confirm_password_edittxt.getText().toString().equals(password_edittxt.getText().toString())){
                   confirm_password_edittxt.setError("Password does not match");
                   return;
               }
           }
       });
        return true;
    }


    public class  New_user_asyncktask extends AsyncTask<String, Void, String>{
        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getApplicationContext(), "my name is" +s, Toast.LENGTH_LONG);
        }

        @Override
        protected String doInBackground(String... strings) {

            final String name = name_edittxt.getText().toString();
            final String email = email_edittxt.getText().toString();
            String password = password_edittxt.getText().toString();
            saveData(name, email, password);
            return name;
        }

        public void saveData(final String name, final String email, String password){
           userApiService.insert_data(name, email, password).enqueue(new Callback<New_user>() {
               @Override
               public void onResponse(Call<New_user> call, Response<New_user> response) {
                   if(response.isSuccessful()) {
                       Intent intent = new Intent(getApplicationContext(), set_profile_activity.class);
                       intent.putExtra("name", name);
                       intent.putExtra("email", email);
                       startActivity(intent);
                       Log.d("Beatrice", response.body().toString());
                   }
               }

               @Override
               public void onFailure(Call<New_user> call, Throwable t) {
                   Log.i("Sign_up_activity", "Post failed");
               }
           });
        }
    }

}
