package com.journey.app;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initToolbar();
        initRegisterButton();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initRegisterButton() {
        Button registerButton = (Button) findViewById(R.id.button_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                register();
            }
        });
    }

    private void register() {
        TextInputEditText phoneEditor = (TextInputEditText) findViewById(R.id.edit_phone);
        TextInputEditText passwordEditor = (TextInputEditText) findViewById(R.id.edit_password);
        String phone = phoneEditor.getText().toString();
        String password = phoneEditor.getText().toString();

        // Todo: register with phone and password
    }

}
