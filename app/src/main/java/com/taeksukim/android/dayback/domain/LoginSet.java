package com.taeksukim.android.dayback.domain;

/**
 * Created by TaeksuKim on 2017. 4. 20..
 */

public class LoginSet {
    String email;
    String password;
    String nickname;
    String profile_photo;

    public LoginSet(String email, String password, String nickname){
        this.email = email;
        this.password = password;
        this.nickname = nickname;

    }
}
