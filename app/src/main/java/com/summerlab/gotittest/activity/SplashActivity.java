package com.summerlab.gotittest.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.Button;

import com.summerlab.gotittest.R;


public class SplashActivity extends Activity implements View.OnClickListener {

    private Button btnLogin;
    private Button btnFbLogin;

    private SharedPreferences mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mRef = getSharedPreferences("GotItTest", MODE_PRIVATE);
        if (!"".equals(mRef.getString("AUTH_KEY", ""))) {
            startActivity(new Intent(this, MainActivity.class));
        }

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                break;

            default:
                break;
        }
    }
}
