package com.taeksukim.android.dayback;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.taeksukim.android.dayback.domain.LoginSet;
import com.taeksukim.android.dayback.domain.SignupResponse;
import com.taeksukim.android.dayback.server.ApiServer;
import com.taeksukim.android.daybacklogin.R;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {



    EditText editUpid, editUpname, editUppw;
    LinearLayout layoutSignup;

    //retrofit

    Retrofit retrofit;
    ApiServer apiserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editUpid = (EditText) findViewById(R.id.editUpid);
        editUpname = (EditText) findViewById(R.id.editUpname);
        editUppw = (EditText) findViewById(R.id.editPw);

        layoutSignup = (LinearLayout) findViewById(R.id.layoutSignup);

//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//        httpClient.addInterceptor(logging);

        RequestBody email = RequestBody.create(MultipartBody.FORM, "tafgfsrfa@naver.com");
        RequestBody password = RequestBody.create(MultipartBody.FORM, "alalalal");
        RequestBody nickname = RequestBody.create(MultipartBody.FORM, "alalalal");

        //retrofit
        retrofit = new Retrofit.Builder()
//                .client(httpClient.build())
                .baseUrl(ApiServer.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiserver= retrofit.create(ApiServer.class);



        LoginSet loginSet = new LoginSet("tata@naver.com", "asdasdasd","alalal");
        Call<SignupResponse> result = apiserver.getSignUpData(
                loginSet

        );


        result.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if(response.isSuccessful() && response.body() != null) {
                    SignupResponse output = response.body();
                    Log.i("output" ,output.password+"");
                    Log.i("output" ,output.email+"");
                    Log.i("output" ,output.nickname+"");
                    Log.i("server!!!!!!!!!!!", response.body().toString());
                }else if(response.isSuccessful()){
                    Log.i("Response Body Null", response.message());
                }else {
                    try {
                        //404
                        Log.i("Response Error Body", response.code() + "!!!!! " + response.errorBody().string());
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                Log.i("Error", t.getMessage());
            }


        });

    }
}
