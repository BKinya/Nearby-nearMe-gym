package com.beatrice.nearby_nearme_gym.activities;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.content.AsyncTaskLoader;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.beatrice.nearby_nearme_gym.R;
import com.beatrice.nearby_nearme_gym.model.LoginResponseObj;
import com.beatrice.nearby_nearme_gym.rest.ApiUtilities;
import com.beatrice.nearby_nearme_gym.rest.UserApiService;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login_activity extends LifecycleLoggingActivity implements LoaderManager.LoaderCallbacks<String> {

    private static UserApiService userApiService;

    private LoginButton fb_login_btn;
    private CallbackManager callbackManager;

    private static EditText email_txt_view, password_txt_view;
    private Button login_btn_btn;
    private TextView forgot_password_txt, create_account_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        //get instance of UserApiService class
        userApiService = ApiUtilities.getUserApiService();

        fb_login_btn = findViewById(R.id.fb_login_button);

        email_txt_view = findViewById(R.id.email_edit_txt);
        password_txt_view = findViewById(R.id.password_edit_txt);

        login_btn_btn = findViewById(R.id.login_btn_login);

        forgot_password_txt = findViewById(R.id.forgot_password_textview);
        create_account_txt = findViewById(R.id.sign_up_txt_login);

        create_account_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(login_activity.this, sign_up_activity.class);
                startActivity(i);
            }
        });
        login_btn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isInputValid()){
                    Log.i("TAG", "empty fields");
                    return;
                }
                getSupportLoaderManager().initLoader(0, null, login_activity.this);
            }
        });

        //register callback
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }

    /**
     * check if user input is valid
     *
     * @return boolean
     */

    public boolean isInputValid() {
        if (TextUtils.isEmpty(email_txt_view.getText().toString())) {
            email_txt_view.setError("Invalid email address");
            return false;
        }
        if (TextUtils.isEmpty(password_txt_view.getText().toString())) {
            password_txt_view.setError("Invalid Password");
            return false;
        }

        return true;
    }

    /**
     * @param requestCode
     * @param resultCode
     * @param data
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new LoginAsyncTaskLoader(this);
    }



    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    //AsyncTaskLoader to authenticate the user
    private static class LoginAsyncTaskLoader extends AsyncTaskLoader<String> {

        private Context context = getContext();

        public LoginAsyncTaskLoader(Context context) {
            super(context);
        }

        @Override
        protected void onStartLoading() {
            super.onStartLoading();
            forceLoad();
        }

        @Override
        protected void onStopLoading() {
            super.onStopLoading();
            cancelLoad();
        }

        @Nullable
        @Override
        public String loadInBackground() {
            String message = null;
            final String email = email_txt_view.getText().toString();
            String password = password_txt_view.getText().toString();
            Call<LoginResponseObj>call = login(email, password);
             call.enqueue(new Callback<LoginResponseObj>() {
                 @Override
                 public void onResponse(Call<LoginResponseObj> call, Response<LoginResponseObj> response) {
                     if (response.isSuccessful()){
                          if (response.body().getMessage().equals("user does not exist")){
                              Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                              onStopLoading();
                          }else if(response.body().getMessage().equals("invalid login")){
                              Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                              onStopLoading();
                          }else if(response.body().getMessage().equals("success")){
                                Intent i = new Intent(context, Locations_in_Africa.class);
                                i.putExtra("email", email);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(i);
                          }
                     }
                 }

                 @Override
                 public void onFailure(Call<LoginResponseObj> call, Throwable t) {
                     Log.i("TAG", t.getMessage());

                 }
             });
            return message;
        }

        public Call<LoginResponseObj> login(String email, String password) {
            Call call = userApiService.login(email, password);
            return  call;
        }

        public static String loginResponse(String mesaage){
            return mesaage;
        }

    }


}








