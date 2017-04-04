package com.taeksukim.android.dayback;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.taeksukim.android.daybacklogin.R;

import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int RESOLVE_CONNECTION_REQUEST_CODE = 1;



    EditText editId, editPw;
    CheckBox autoLogin;
    Button btnLogin, btnSignup, LoginButton;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Boolean loginCheck;
    private CallbackManager callbackManager;
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editId = (EditText) findViewById(R.id.editId);
        editPw = (EditText) findViewById(R.id.editPw);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        autoLogin = (CheckBox) findViewById(R.id.autoLogin);

        // 페이스 북
        callbackManager = CallbackManager.Factory.create();

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.i("result", "object :" + object.toString());
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        //intent에 페이스북 유저 정보를 담는다.
                        String[] userString = new String[] {object.toString()};
                        intent.putExtra("FacebookUser", object.toString());
//                        intent.putExtra("id", resultId.toString());
                        startActivity(intent);
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, name, email, gender, birthday");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(),"Login error",Toast.LENGTH_SHORT).show();
            }
        });


        //구글 로그인
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(LoginActivity.this)
                .enableAutoManage(LoginActivity.this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(LoginActivity.this, "Login error", Toast.LENGTH_LONG).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sign_in_button:
                        signIn();

                        break;

                }
            }
        });







        //로그인
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //사용자 추가
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });



        //SharedPreference
        pref = getSharedPreferences("pref", 0);
        editor = pref.edit();

        // 자동 로그인
        if(pref.getBoolean("autoLogin", false)){
            editId.setText(pref.getString("id", ""));
            editPw.setText(pref.getString("password", ""));
            autoLogin.setChecked(true);

        }

        autoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Log.i("aaa", "ischecked : " + isChecked+"");
                    String id = editId.getText().toString();
                    String password = editPw.getText().toString();

                    editor.putString("id", id);
                    editor.putString("password", password);
                    editor.putBoolean("autoLogin", true);

                    editor.commit();




                }else{
                    Log.i("aaa", "ischecked : " + isChecked+"");
                    editor.clear();
                    editor.commit();

                }
            }
        });
    }

    //구글 로그인
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RESOLVE_CONNECTION_REQUEST_CODE);

    }

    private boolean loginValidation(String id, String password) {
        if(pref.getString("id","").equals(id) && pref.getString("pw","").equals(password)) {
            // login success
            return true;
        } else if (pref.getString("id","").equals(null)){
            // sign in first
            Toast.makeText(this, "Please Sign in first", Toast.LENGTH_LONG).show();
            return false;
        } else {
            // login failed
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode,data);

        switch ( requestCode ) {
            case RESOLVE_CONNECTION_REQUEST_CODE:
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent( data );
                if ( result.isSuccess( ) ) {
                    GoogleSignInAccount acct = result.getSignInAccount( );
                    // 계정 정보 얻어오기
                    Log.i( "google", acct.getDisplayName( ) );
                    Log.i( "google", acct.getEmail( ) );
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    //intent에 구글 유저 정보를 담는다.
                    //String[] userString = new String[] {acct.getDisplayName().toString()};
                    intent.putExtra("Google user", acct.getDisplayName().toString());
                    //String[] userEmail = new String[]{acct.getEmail().toString()};
                    intent.putExtra("Google email", acct.getEmail().toString());
                    Log.i("google", "" +intent);
//                  intent.putExtra("id", resultId.toString());
                    startActivity(intent);
                }
                     break;

                     default:
                     super.onActivityResult( requestCode, resultCode, data );
                }




    }
}
