package com.taeksukim.android.dayback;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.logging.SimpleFormatter;

/**
 * Created by pc on 1/31/2017.
 */

public class Data {

    private ArrayList<User> datas;

    long now = System.currentTimeMillis();
    Date date = new Date(now);
    SimpleDateFormat today = new SimpleDateFormat("yyyy년 MM월 dd일 E HH:mm a");
    String strToday = today.format(now);


    public ArrayList<User> get(){
        return datas;
    }

    public Data(){
        datas = new ArrayList<>();
        load();
    }

    private void load(){
        // 특정범위의 무작위 난수를 생성해준다.
        Random random = new Random();

        // datas 에 100명의 User를 생성해서 담는다
        for(int i=0 ; i<1000 ; i++) {
            User user = new User();
            user.title   = "title"+i+1;
            user.name = "홍길동 " + i;
            user.date = strToday;
            Log.i("Random","number=" + user.date);
            datas.add(user);
        }
    }
}