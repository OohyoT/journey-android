package com.journey.app.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.journey.app.R;
import com.journey.app.api.AuthApi;
import com.journey.app.api.JourneyApi;
import com.journey.app.model.AuthInfo;
import com.journey.app.model.LoginResult;
import com.journey.app.model.Result;
import com.journey.app.util.Api;
import com.journey.app.util.Auth;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.edit_phone) TextInputEditText phoneEditor;
    @BindView(R.id.edit_password) TextInputEditText passwordEditor;
    @BindView(R.id.button_login) Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        initToolbar();
        initLoginButton();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initLoginButton() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        String phone = phoneEditor.getText().toString();
        String password = passwordEditor.getText().toString();

        AuthApi api = Api.getAuthApiInstance();
        Call<LoginResult> login = api.login(new AuthInfo(phone, password));
        login.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                LoginResult result = response.body();
                Toast.makeText(getApplicationContext(), result.message, Toast.LENGTH_SHORT).show();
                if (result.code.equals(Result.SUCCESS)) {
                    Auth.login(result.loginUserId);
                    setResult(RESULT_OK);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
