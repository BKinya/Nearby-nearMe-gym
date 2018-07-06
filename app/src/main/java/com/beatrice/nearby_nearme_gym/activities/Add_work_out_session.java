package com.beatrice.nearby_nearme_gym.activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.beatrice.nearby_nearme_gym.R;
import com.beatrice.nearby_nearme_gym.activities.fragments.DateFragment;
import com.beatrice.nearby_nearme_gym.model.Work_out_session_model;
import com.beatrice.nearby_nearme_gym.rest.ApiUtilities;
import com.beatrice.nearby_nearme_gym.rest.Work_out_session_service;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Intent.*;

public class Add_work_out_session extends FragmentActivity implements LoaderManager.LoaderCallbacks<String>{

    private static EditText date_edit_txt, sets_edittxt_view;
    private Button date_btn, save_btn;
    private static Spinner mSpinner;

    private static Context context;
    //retrofit interface instance
    private  static Work_out_session_service work_out_session_service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work_out_session);

        date_edit_txt = findViewById(R.id.date_txt_view);
        date_btn = findViewById(R.id.date_btn);
        mSpinner = findViewById(R.id.exercise_cat);
        sets_edittxt_view = findViewById(R.id.no_of_sets);
        save_btn = findViewById(R.id.btn_save);

        //instantiate retrofit interface
        work_out_session_service = ApiUtilities.getWork_out_session_service();

        date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.DialogFragment dateFragment = new DateFragment();
                dateFragment.show(getSupportFragmentManager(), getString(R.string.date));
            }
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Add_work_out_session.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.exercise_cat));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportLoaderManager().initLoader(0, null, Add_work_out_session.this);
            }
        });
    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        // Assign the concatenated strings to dateMessage.
        String dateMessage = (day_string+ "/" +
                month_string + "/" + year_string);
        date_edit_txt.setText(dateMessage);
    }

    public  String getusersEmail(){
        Intent i = getIntent();
        String email = i.getStringExtra("email");
        return  email;
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new WorkoutAsyncyaskLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        Intent i = new Intent(getApplicationContext(), Locations_in_Africa.class);
        startActivity(i);
        Log.i("Success", data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }



    //simple asynctaskloader class to make network calls in the background
    private static  class WorkoutAsyncyaskLoader extends AsyncTaskLoader<String>{

        public WorkoutAsyncyaskLoader(@NonNull Context context) {
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

            String email = "gitongakinya1@gmail.com";
            String date = date_edit_txt.getText().toString();
            String exercise = mSpinner.getSelectedItem().toString();
            String no_of_sets = sets_edittxt_view.getText().toString();
            save_workout_session(email, date, exercise, no_of_sets);
            return date;
        }

        public void save_workout_session(String email, String date, String exercise, String no_of_sets){
            work_out_session_service.add_workout_session(email, date, exercise, no_of_sets).enqueue(new Callback<Work_out_session_model>() {
                @Override
                public void onResponse(Call<Work_out_session_model> call, Response<Work_out_session_model> response) {
                    if (response.isSuccessful()){
                        Log.i("TAG", "Success");
                    }
                }

                @Override
                public void onFailure(Call<Work_out_session_model> call, Throwable t) {
                    Log.i("Error", t.getMessage());
                }
            });
        }
    }
}
