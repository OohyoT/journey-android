package com.journey.app.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.journey.app.R;
import com.journey.app.model.AuthInfo;
import com.journey.app.util.Api;
import com.journey.app.api.JourneyApi;
import com.journey.app.model.Result;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.edit_phone) TextInputEditText phoneEditor;
    @BindView(R.id.edit_password) TextInputEditText passwordEditor;
    @BindView(R.id.button_register) Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);

        initToolbar();
        initRegisterButton();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initRegisterButton() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                register();
            }
        });
    }

    private void register() {
        String phone = phoneEditor.getText().toString();
        String password = phoneEditor.getText().toString();

        JourneyApi service = Api.getApiInstance();
        Call<Result> register = service.register(new AuthInfo(phone, password));
        register.enqueue(new Callback<Result>() {
            @Override public void onResponse(Call<Result> call, Response<Result> response) {
                Log.i("Register", response.message());
            }

            @Override public void onFailure(Call<Result> call, Throwable t) {
                Log.e("Register", t.getLocalizedMessage());
            }
        });
    }

}
