package com.taeksukim.android.dayback.server;

import com.taeksukim.android.dayback.Data;
import com.taeksukim.android.dayback.domain.LoginSet;
import com.taeksukim.android.dayback.domain.SignUpData;
import com.taeksukim.android.dayback.domain.SignupResponse;
import com.taeksukim.android.dayback.domain.UserData;
import com.taeksukim.android.dayback.domain.UserResponse;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;


import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by TaeksuKim on 2017. 4. 14..
 */

public interface ApiServer {

    public static final String API_URL = "https://dayback.hcatpr.com/";
//    public static final String API_URL = "http://192.168.0.194/";

//    @Multipart
//    @POST("signup/")
//    Call<SignupResponse>getSignUpData(@Part("email") RequestBody email
//                                ,@Part("password") RequestBody password
//                                ,@Part("nickname") RequestBody nickname
//                                ,@Part("profile_photo") String profile_photo);

    @POST("signup/")
    Call<SignupResponse>getSignUpData(
            @Body LoginSet set

    );


//    Call<SignupResponse>getSignUpData(@Field("email") String email
//                                    , @Field("password") String password
//                                    , @Field("nickname") String nickname
//                                    , @Field("profile_photo") String profile_photo);

    @Multipart
    @POST("login/")
    Call<UserResponse>getUserData(@Part("email") String email
                        , @Part("password") String password);

}
