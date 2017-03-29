package com.taeksukim.android.dayback;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.taeksukim.android.daybacklogin.R;

public class SignUpActivity extends AppCompatActivity {


    EditText editUpid, editUpname, editUppw;
    LinearLayout layoutSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editUpid = (EditText) findViewById(R.id.editUpid);
        editUpname = (EditText) findViewById(R.id.editUpname);
        editUppw = (EditText) findViewById(R.id.editPw);

        layoutSignup = (LinearLayout) findViewById(R.id.layoutSignup);

    }
}
