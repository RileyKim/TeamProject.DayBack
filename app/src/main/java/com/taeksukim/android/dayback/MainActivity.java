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


import com.taeksukim.android.daybacklogin.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    ArrayList<User> datas;
    RecyclerView rv;
    ImageView whitePen, whiteAvatar, whiteGraph, whiteCalendar, whiteAlarm;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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
