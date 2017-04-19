package com.taeksukim.android.dayback.server;

import com.taeksukim.android.dayback.Data;
import com.taeksukim.android.dayback.domain.SignUpData;
import com.taeksukim.android.dayback.domain.SignupResponse;
import com.taeksukim.android.dayback.domain.UserData;
import com.taeksukim.android.dayback.domain.UserResponse;

import retrofit2.Call;


import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by TaeksuKim on 2017. 4. 14..
 */

public interface ApiServer {

    public static final String API_URL = "http://dayback.hcatpr.com/";

    @Multipart
    @POST("signup")
    Call<SignupResponse>getSignUpData(@Part("email") String email
                                ,@Part("password") String password
                                ,@Part("nickname") String nickname
                                ,@Part("profile_photo") String profile_photo);

//    Call<SignupResponse>getSignUpData(@Field("email") String email
//                                    , @Field("password") String password
//                                    , @Field("nickname") String nickname
//                                    , @Field("profile_photo") String profile_photo);

    @Multipart
    @POST("login/")
    Call<UserResponse>getUserData(@Part("email") String email
                        , @Part("password") String password);

}
