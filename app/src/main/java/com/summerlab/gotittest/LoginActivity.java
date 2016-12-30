package com.summerlab.gotittest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.summerlab.gotittest.utils.LogUtils;
import com.summerlab.gotittest.utils.rest.ApiClient;
import com.summerlab.gotittest.utils.rest.ApiInterface;
import com.summerlab.gotittest.utils.rest.CustomCall;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private ActionBar mActionBar;
    private Button btnLogin;
    private EditText etEmail;
    private EditText etPassword;

    private SharedPreferences mRef;

    private ApiInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        apiService = ApiClient.getClient().create(ApiInterface.class);

        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);
        mActionBar.setTitle(getString(R.string.log_in));

        //Layout
        btnLogin = (Button) findViewById(R.id.btn_login);
        etEmail = (EditText) findViewById(R.id.text_email);
        etPassword = (EditText) findViewById(R.id.text_password);

        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                logIn();
                break;

            default:
                break;
        }
    }

    private void logIn() {
        RequestBody email = RequestBody.create(MediaType.parse("text/plain"), etEmail.getText().toString());
        RequestBody password = RequestBody.create(MediaType.parse("text/plain"), etPassword.getText().toString());

        Call<ResponseBody> call = apiService.logIn(email, password);

        call.enqueue(new CustomCall<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    try {
                        JSONObject json = new JSONObject(response.body().string());
                        LogUtils.d("trieulh", json.getString("auth"));
                        mRef = getSharedPreferences("GotItTest", MODE_PRIVATE);
                        SharedPreferences.Editor editor = mRef.edit();
                        editor.putString("AUTH_KEY", json.getString("auth"));
                        editor.commit();

                        Toast.makeText(getApplicationContext(), "Login successfully", Toast.LENGTH_LONG).show();

                        startActivity(new Intent(LoginActivity.this, MainActivity.class));

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            protected void showDialog() {
                showMaintainDialog();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
