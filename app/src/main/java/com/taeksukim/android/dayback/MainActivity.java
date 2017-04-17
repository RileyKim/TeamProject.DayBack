package com.taeksukim.android.dayback;

import android.content.Intent;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.widget.ImageView;


import com.taeksukim.android.dayback.domain.SignUpData;
import com.taeksukim.android.dayback.server.ApiServer;
import com.taeksukim.android.daybacklogin.R;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    ArrayList<User> datas;
    RecyclerView rv;
    ImageView whitePen, whiteAvatar, whiteGraph, whiteCalendar, whiteAlarm;

    //retrofit
    private SignUpData data;
    Retrofit retrofit;
    ApiServer apiserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //retrofit
        retrofit = new Retrofit.Builder().baseUrl(ApiServer.API_URL).addConverterFactory(GsonConverterFactory.create()).build();

        apiserver= retrofit.create(ApiServer.class);

        Call<SignUpData> result = apiserver.getSignUpData();


        result.enqueue(new Callback<SignUpData>() {
            @Override
            public void onResponse(Call<SignUpData> call, Response<SignUpData> response) {
                if(response.isSuccessful() && response.body() != null) {
                    data = response.body();
                    Log.i("server!!!!!!!!!!!", response.body().toString());
                }else if(response.isSuccessful()){
                    Log.i("Response Body Null", response.message());
                }else {
                    //404
                    Log.i("Response Error Body", response.errorBody().toString());
                }

            }

            @Override
            public void onFailure(Call<SignUpData> call, Throwable t) {
                Log.i("Error", t.getMessage());
            }


        });

        //------------------------------------------
        //LoginActivity에서 MainActivity로 로그인 유저 정보가 잘 넘어오는지 확인
        Intent intent = getIntent();
        String s = intent.getStringExtra("user");
        String t = intent.getStringExtra("email");
        Log.i("MainActivity", "user : " + s);
        Log.i("MainActivity", "email :" + t);
        //------------------------------------------

        //------------------------------------------

//

        Data data = new Data();
        datas = data.get();
        viewPager = (ViewPager)  findViewById(R.id.viewPager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        //------------------------------------------
        // MainActivity 하단 메모 이미지 버튼
        whitePen = (ImageView) findViewById(R.id.whitePen);
        whitePen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MemoActivity.class);
                startActivity(intent);
            }
        });
        //------------------------------------------


        //------------------------------------------
        // MainActivity 하단 graph 이미지 버튼
        whiteGraph = (ImageView) findViewById(R.id.whiteGraph);
        whiteGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GraphActivity.class);
                startActivity(intent);
            }
        });
        //------------------------------------------


        //------------------------------------------
        // MainActivity 하단 avatar 이미지버튼
        whiteAvatar = (ImageView) findViewById(R.id.whiteAvatar);
        whiteAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
        //------------------------------------------

        //------------------------------------------
        // whiteCalendar 이미지 버튼
        whiteCalendar = (ImageView) findViewById(R.id.whiteCalendar);
        whiteCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
        //------------------------------------------

        //------------------------------------------
        // whiteAlarm 이미지 버튼
        whiteAlarm = (ImageView) findViewById(R.id.whiteAlarm);
        whiteAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //------------------------------------------


    }


    private class PagerAdapter extends FragmentStatePagerAdapter{

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PageFragment.create(position);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }





}
