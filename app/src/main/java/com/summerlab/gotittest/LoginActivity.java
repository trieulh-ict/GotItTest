package com.summerlab.gotittest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.summerlab.gotittest.controller.UserController;
import com.summerlab.gotittest.utils.LogUtils;
import com.summerlab.gotittest.utils.networkutils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ActionBar mActionBar;
    private Button btnLogin;
    private EditText etEmail;
    private EditText etPassword;

    private UserController mUserController;
    private SharedPreferences mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUserController = new UserController(this);

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
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        try {
            mUserController.logInUser(email, password, new NetworkUtils.NetworkListener() {
                @Override
                public void onSuccess(JSONObject jsonResponse) {
                    Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_LONG).show();
                    try {
                        mRef = getSharedPreferences("GotItTest", MODE_PRIVATE);
                        SharedPreferences.Editor editor = mRef.edit();
                        editor.putString("AUTH_KEY", jsonResponse.getString("auth"));
                        editor.commit();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }

                @Override
                public void onError(VolleyError volleyError) {
                    Toast.makeText(getApplicationContext(), "Wrong Username & Password", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onSuccess(JSONArray jsonObject) {

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
