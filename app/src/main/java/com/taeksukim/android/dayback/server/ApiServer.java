package com.taeksukim.android.dayback.server;

import com.taeksukim.android.dayback.Data;
import com.taeksukim.android.dayback.domain.SignUpData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by TaeksuKim on 2017. 4. 14..
 */

public interface ApiServer {

    public static final String API_URL = "https://dayback.hcatpr.com/";

    @POST("signup")
    Call<SignUpData>getSignUpData();

}
