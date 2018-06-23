package com.beatrice.nearby_nearme_gym.activities;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


import com.beatrice.nearby_nearme_gym.R;

public class sign_up_activity extends LifecycleLoggingActivity {
    //views
    private EditText name_edittxt, email_edittxt, password_edittxt, confirm_password_edittxt;
    private Button signup_btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_activity);

        name_edittxt = findViewById(R.id.name_edit_txt);
        email_edittxt = findViewById(R.id.email_edit_txt);
        password_edittxt = findViewById(R.id.password_edit_txt);
        confirm_password_edittxt = findViewById(R.id.confirm_password_edit_txt);

        signup_btn_signup = findViewById(R.id.signup_btn_signup);

    }
}
