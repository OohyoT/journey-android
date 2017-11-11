package com.journey.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.journey.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EntryActivity extends AppCompatActivity {

    private final static int REQUEST_LOGIN = 0;

    @BindView(R.id.button_register) AppCompatButton registerButton;
    @BindView(R.id.link_login) AppCompatTextView loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        initRegisterButton();
        initLoginLink();
    }

    private void initRegisterButton() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                startActivity(new Intent(EntryActivity.this, RegisterActivity.class));
            }
        });
    }

    private void initLoginLink() {
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                startActivityForResult(
                        new Intent(EntryActivity.this, LoginActivity.class),
                        REQUEST_LOGIN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_LOGIN && resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish();
        }
    }
}
