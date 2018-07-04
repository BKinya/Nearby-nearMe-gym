package com.beatrice.nearby_nearme_gym.activities;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.beatrice.nearby_nearme_gym.R;
import com.beatrice.nearby_nearme_gym.activities.fragments.DateFragment;
import com.beatrice.nearby_nearme_gym.activities.fragments.TimeFragment;

public class Add_work_out_session extends AppCompatActivity {

    private EditText date_edit_txt, time_edit_txt;
    private Button date_btn, time_btn;
    private Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work_out_session);

        date_edit_txt = findViewById(R.id.date_txt_view);
        time_edit_txt = findViewById(R.id.time_txt_view);
        date_btn = findViewById(R.id.date_btn);
        time_btn = findViewById(R.id.time_btn);
        mSpinner = findViewById(R.id.exercise_cat);


        date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.DialogFragment dateFragment = new DateFragment();
                dateFragment.show(getSupportFragmentManager(), getString(R.string.date));
            }
        });

        time_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.DialogFragment timeFragment = new TimeFragment();
                timeFragment.show(getSupportFragmentManager(), getString(R.string.time));
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Add_work_out_session.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.exercise_cat));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        // Assign the concatenated strings to dateMessage.
        String dateMessage = (month_string + "/" +
                day_string + "/" + year_string);
        date_edit_txt.setText(dateMessage);

    }

    public void processTimePickerResult(int hourOfDay, int minute) {
        // Convert time elements into strings.
        String hour_string = Integer.toString(hourOfDay);
        String minute_string = Integer.toString(minute);
        // Assign the concatenated strings to timeMessage.
        String timeMessage = (hour_string + ":" + minute_string);
        time_edit_txt.setText(timeMessage);
    }
}
