package com.beatrice.nearby_nearme_gym.activities;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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

        isUserInputValid();

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

}
