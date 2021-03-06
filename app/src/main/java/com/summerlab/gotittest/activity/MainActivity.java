package com.summerlab.gotittest.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.summerlab.gotittest.R;
import com.summerlab.gotittest.model.QuestionResponse;
import com.summerlab.gotittest.model.adapter.QuestionAdapter;
import com.summerlab.gotittest.utils.LogUtils;
import com.summerlab.gotittest.utils.rest.ApiClient;
import com.summerlab.gotittest.utils.rest.ApiInterface;
import com.summerlab.gotittest.utils.rest.CustomCall;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SharedPreferences mRef;
    private RecyclerView listQuestionsView;

    private ApiInterface apiService;

    private QuestionAdapter mQuestionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService = ApiClient.getClient().create(ApiInterface.class);

        initView();

        initData();
    }

    private void initView() {
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

        listQuestionsView = (RecyclerView) findViewById(R.id.list_questions);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        listQuestionsView.setLayoutManager(mLayoutManager);
        listQuestionsView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initData() {
        getQuestions();
    }

    private void getQuestions() {
        mRef = getSharedPreferences("GotItTest", MODE_PRIVATE);
        Call<List<QuestionResponse>> call = apiService.getQuestions(mRef.getString("AUTH_KEY", ""));

        call.enqueue(new CustomCall<List<QuestionResponse>>() {
            @Override
            public void onResponse(Call<List<QuestionResponse>> call, Response<List<QuestionResponse>> response) {
                super.onResponse(call, response);
                LogUtils.d("trieulh", "Response" + response.code() + "");
                if (response.isSuccessful()) {
                    List<QuestionResponse> questions = response.body();
                    mQuestionAdapter = new QuestionAdapter(getApplicationContext(), questions);
                    listQuestionsView.setAdapter(mQuestionAdapter);
                }
            }

            @Override
            protected void showDialog() {
                showMaintainDialog();
            }

            @Override
            public void onFailure(Call<List<QuestionResponse>> call, Throwable t) {
                LogUtils.d("trieulh", "Fail");
            }
        });
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
        mRef = getSharedPreferences("GotItTest", MODE_PRIVATE);
        SharedPreferences.Editor editor = mRef.edit();
        editor.putString("AUTH_KEY", "");
        editor.commit();
        startActivity(new Intent(this, LoginActivity.class));
    }
}
