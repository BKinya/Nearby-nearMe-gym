package com.beatrice.nearby_nearme_gym.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.beatrice.nearby_nearme_gym.R;
import com.beatrice.nearby_nearme_gym.model.User_profile;
import com.beatrice.nearby_nearme_gym.rest.ApiUtilities;
import com.beatrice.nearby_nearme_gym.rest.UserProfileApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public  class set_profile_activity extends LifecycleLoggingActivity implements LoaderManager.LoaderCallbacks<String> {

    private static UserProfileApiService muserProfileApiService;

    String[] gender = {"Male", "Female", "Other", "Prefer not to disclose"};
    //views
    private static EditText name_edit_txt, email_edit_txt, phone_edit_txt, age_edit_txt, c_weight_txt, t_weight_txt;
    private static AutoCompleteTextView gender_txtview;

    private Button save_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_profile_activity);

        //initialize UserProfileApiService
        muserProfileApiService = ApiUtilities.getUserProfileApi();



        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");

        gender_txtview = findViewById(R.id.gender_txtview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, gender);
        gender_txtview.setThreshold(1);
        gender_txtview.setAdapter(adapter);

        name_edit_txt = findViewById(R.id.name_edit_txt);
        email_edit_txt = findViewById(R.id.email_edit_txt);
        phone_edit_txt = findViewById(R.id.phone_edit_txt);
        age_edit_txt = findViewById(R.id.age_edit_txt);
        c_weight_txt = findViewById(R.id.weight_edit_txt);
        t_weight_txt = findViewById(R.id.target_weight_txt);
        save_btn = findViewById(R.id.btn_save);

        name_edit_txt.setText(name);
        email_edit_txt.setText(email);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportLoaderManager().initLoader(0, null, set_profile_activity.this);
            }
        });


    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new UserProfile(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        Intent i= new Intent(set_profile_activity.this, Locations_in_Africa.class);
        startActivity(i);
        Log.d("Beatrice", data);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    /**
     * static simple inner class that extends asynctaskloader
     */
    private static class UserProfile extends AsyncTaskLoader<String> {

        public UserProfile(Context context) {
            super(context);
        }

        @Override
        protected void onStartLoading() {
            super.onStartLoading();
            forceLoad();
        }

        @Nullable
        @Override
        public String loadInBackground() {


            String name = name_edit_txt.getText().toString();
            String email = email_edit_txt.getText().toString();
            String phone = phone_edit_txt.getText().toString();
            String gender = gender_txtview.getText().toString();
            String age = age_edit_txt.getText().toString();
            String c_weight = c_weight_txt.getText().toString();
            String t_weight = t_weight_txt.getText().toString();

            saveData(name, email, phone, gender, age, c_weight, t_weight);

            return name;
        }

        public void saveData(String name, String email, String phone, String gender, String age, String c_weight, String t_weight) {
            muserProfileApiService.saveProfile(name, email, phone, gender, age, c_weight, t_weight).enqueue(new Callback<User_profile>() {
                @Override
                public void onResponse(Call<User_profile> call, Response<User_profile> response) {



                }

                @Override
                public void onFailure(Call<User_profile> call, Throwable t) {
                    Log.i("Beatrice", "Unable to submit post");
                }
            });
        }
    }
}
