package com.summerlab.gotittest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.summerlab.gotittest.controller.QuestionController;
import com.summerlab.gotittest.model.Question;
import com.summerlab.gotittest.utils.LogUtils;
import com.summerlab.gotittest.utils.Utilities;
import com.summerlab.gotittest.utils.networkutils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SharedPreferences mRef;
    private QuestionController mQuestionController;
    private RecyclerView listQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initView();

        initData();
    }

    private void initView() {
        listQuestions = (RecyclerView) findViewById(R.id.list_questions);
    }

    private void initData() {
        mQuestionController = new QuestionController(this);

        try {
            mQuestionController.getQuestions(new NetworkUtils.NetworkListener() {
                @Override
                public void onSuccess(JSONObject jsonResponse) {
                    LogUtils.d("trieulh", "SUCCESS");
                }

                @Override
                public void onError(VolleyError volleyError) {
                    LogUtils.d("trieulh", "ERROR");
                }

                @Override
                public void onSuccess(JSONArray jsonObject) {
                    parseQuestionList(jsonObject);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.id_logout) {
            // Handle the Log out action
            logOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logOut() {

        //TODO Call Logout API

        //

        mRef = getSharedPreferences("GotItTest", MODE_PRIVATE);
        SharedPreferences.Editor editor = mRef.edit();
        editor.putString("AUTH_KEY", "");
        editor.commit();
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void parseQuestionList(JSONArray jsonObject) {
        Gson gson = Utilities.getGSON();
        Type listType = new TypeToken<ArrayList<Question>>() {
        }.getType();
        ArrayList<Question> questionList = gson.fromJson(jsonObject.toString(), listType);

        LogUtils.d("trieulh", "Size " + questionList.size());
    }
}
