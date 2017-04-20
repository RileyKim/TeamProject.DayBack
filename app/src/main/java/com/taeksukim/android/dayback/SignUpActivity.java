package com.taeksukim.android.dayback;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

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



    EditText editUpemail, editUpname, editUppw;
    Button btnSignup;
    LinearLayout layoutSignup;
    LoginSet loginSet;
    String getemail;
    String getname;
    String getpw;

    //retrofit

    Retrofit retrofit;
    ApiServer apiserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editUpemail = (EditText) findViewById(R.id.editUpemail);
        editUpname = (EditText) findViewById(R.id.editUpname);
        editUppw = (EditText) findViewById(R.id.editUppw);
        btnSignup = (Button) findViewById(R.id.btnSignup);




        layoutSignup = (LinearLayout) findViewById(R.id.layoutSignup);

//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//        httpClient.addInterceptor(logging);

        //retrofit
        retrofit = new Retrofit.Builder()
//                .client(httpClient.build())
                .baseUrl(ApiServer.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiserver= retrofit.create(ApiServer.class);


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                getemail = editUpemail.getText().toString();
                getname = editUpname.getText().toString();
                getpw = editUppw.getText().toString();

                Log.i("editUpemail",editUpemail.getText().toString());
                Log.i("editUppw",editUppw.getText().toString());
                Log.i("editUpname",editUpname.getText().toString());

                if(editUpemail == null || getname == null || getpw == null){
                    Toast.makeText(SignUpActivity.this, "다시 확인하세요",Toast.LENGTH_SHORT).show();
                }else if(getpw.length() < 8){
                    Toast.makeText(SignUpActivity.this, "비밀번호는 8자 이상입니다.", Toast.LENGTH_SHORT).show();
                }else{
                    mSignup();
                    Intent signupintent = new Intent(SignUpActivity.this, LoginActivity.class);
                    signupintent.putExtra("email",getemail);
                    startActivity(signupintent);
                }
            }
        });
//        //retrofit
//        retrofit = new Retrofit.Builder()
////                .client(httpClient.build())
//                .baseUrl(ApiServer.API_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        apiserver= retrofit.create(ApiServer.class);
//
//
//



//        Call<SignupResponse> result = apiserver.getSignUpData(
//                loginSet
//        );
//
//
//        result.enqueue(new Callback<SignupResponse>() {
//            @Override
//            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
//                if(response.isSuccessful() && response.body() != null) {
//                    SignupResponse output = response.body();
//                    Log.i("output" ,output.password+"");
//                    Log.i("output" ,output.email+"");
//                    Log.i("output" ,output.nickname+"");
//                    Log.i("server!!!!!!!!!!!", response.body().toString());
//                }else if(response.isSuccessful()){
//                    Log.i("Response Body Null", response.message());
//                }else {
//                    try {
//                        //404
//                        Log.i("Response Error Body", response.code() + "!!!!! " + response.errorBody().string());
//                    }catch(IOException e){
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<SignupResponse> call, Throwable t) {
//                Log.i("Error", t.getMessage());
//            }
//
//
//        });

    }

    public void mSignup(){

        loginSet = new LoginSet(getemail, getname,getpw);

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
