package com.musicalcoder.authentication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText editTextFName, editTextLName, editTextUName, editTextEmail,editTextPass1, editTextPass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextFName = findViewById(R.id.fname_field);
        editTextLName = findViewById(R.id.lname_field);
        editTextUName = findViewById(R.id.uname_field);
        editTextEmail = findViewById(R.id.email_field);
        editTextPass1 = findViewById(R.id.password);
        editTextPass2 = findViewById(R.id.confirm_password);

        Button registerBtn = findViewById(R.id.register_btn);
        registerBtn.setOnClickListener(registerListener);

    }

    //    Onclick Event Listener 4 Register Button
    private View.OnClickListener registerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            userRegistration();
        }
    };

    //    Registration Method.
    private void userRegistration(){
        String fName = editTextFName.getText().toString().trim();
        String lName = editTextLName.getText().toString().trim();
        String uName = editTextUName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String pass1 = editTextPass1.getText().toString().trim();
        String pass2 = editTextPass2.getText().toString().trim();

        if(fName.isEmpty()){
            editTextFName.setError("First Name cannot be empty.");
            editTextFName.requestFocus();
            return;
        }
        if(lName.isEmpty()){
            editTextLName.setError("Last Name cannot be empty.");
            editTextLName.requestFocus();
            return;
        }
        if(uName.isEmpty()){
            editTextUName.setError("Username cannot be empty.");
            editTextUName.requestFocus();
            return;
        }
        if(email.isEmpty()){
            editTextEmail.setError("Email cannot be empty.");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Invalid Email format.");
            editTextEmail.requestFocus();
            return;
        }
        if(pass1.isEmpty()){
            editTextPass1.setError("Enter Password.");
            editTextPass1.requestFocus();
            return;
        }
        if(pass2.isEmpty()){
            editTextPass2.setError("Confirm Password.");
            editTextPass2.requestFocus();
            return;
        }

        if(pass1.length() < 8){
            editTextPass1.setError("Password should be at least 8 characters long.");
            editTextPass1.requestFocus();
            return;
        }

        //Use API to do User Registration.
        Call<ResponseBody> registration = Api_Client
                .getInstance()
                .getApi()
                .registerUser(fName, lName, uName, email, pass1, pass2);
        registration.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String resp = response.body().string();
                    Toast.makeText(MainActivity.this, resp, Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }
}
