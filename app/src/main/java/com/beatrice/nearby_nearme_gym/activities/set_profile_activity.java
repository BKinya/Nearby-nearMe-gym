package com.beatrice.nearby_nearme_gym.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.beatrice.nearby_nearme_gym.R;

public class set_profile_activity extends LifecycleLoggingActivity {

    private Spinner gender_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_profile_activity);

        gender_spinner = findViewById(R.id.gender_spinner);

        ArrayAdapter<CharSequence>listAdapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender_spinner.setAdapter(listAdapter);
    }
}
