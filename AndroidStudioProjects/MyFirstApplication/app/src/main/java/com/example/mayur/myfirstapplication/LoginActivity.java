package com.example.mayur.myfirstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    EditText emailSection, passSection;
    Button registerBtn, loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailSection = findViewById(R.id.emailSection);
        passSection = findViewById(R.id.passSection);
        registerBtn = findViewById(R.id.registerBtn);
        loginBtn = findViewById(R.id.loginBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInputValid(emailSection, passSection)){

                    DBManager handler = new DBManager(LoginActivity.this);
                    UserModel user = handler.retrieveUser(emailSection.getText().toString(), passSection.getText().toString());

                    if (user != null) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("USER", user);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "WRONG EMAIL ID OR PASSWORD", Toast.LENGTH_LONG).show();
                    }


                }
            }
        });
    }

    private boolean isInputValid(EditText emailSection, EditText passSection) {

        boolean status = true;

        if (emailSection.getText().toString().length() < 2) {
            emailSection.setError("Please enter valid email ID");
            status = false;
        }

        if (passSection.getText().toString().length() < 2) {
            passSection.setError("Please enter valid password");
            status = false;
        }

        return status;
    }
}
