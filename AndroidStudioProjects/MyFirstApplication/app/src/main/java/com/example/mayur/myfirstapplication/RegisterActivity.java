package com.example.mayur.myfirstapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText emailSection, passSection, firstNameSection, lastNameSection;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailSection = findViewById(R.id.emailSection);
        passSection = findViewById(R.id.passSection);
        firstNameSection = findViewById(R.id.firstNameSection);
        lastNameSection = findViewById(R.id.lastNameSection);
        registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInputValid(emailSection, passSection, firstNameSection, lastNameSection)){
                    UserModel user = new UserModel();
                    user.setUserFName(firstNameSection.getText().toString());
                    user.setUserLName(lastNameSection.getText().toString());
                    user.setUserEmail(emailSection.getText().toString());
                    user.setUserPassword(passSection.getText().toString());
                    DBManager handler = new DBManager(RegisterActivity.this);
                    handler.addNewUser(user);
                    Toast.makeText(RegisterActivity.this, "Registration succesful", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }

    private boolean isInputValid(EditText emailSection, EditText passSection, EditText firstNameSection, EditText lastNameSection) {

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
