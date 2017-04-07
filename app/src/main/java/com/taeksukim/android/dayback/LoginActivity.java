package com.taeksukim.android.dayback;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
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
    Button btnLogin, btnSignup, faceLogin;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Boolean loginCheck;
    private CallbackManager callbackManager;
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;
    String pw, id, facebook, google;
    GoogleSignInAccount acct;
    String faceBooks;
    Intent intent;
    String sfValue;
    String chId;
    String chPw;




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
        faceLogin = (Button) findViewById(R.id.faceLogin);
        faceLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FacebookSdk.sdkInitialize(getApplicationContext());
                callbackManager = CallbackManager.Factory.create();
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this,Arrays.asList("public_profile", "email"));

                LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

                    @Override
                    public void onSuccess(final LoginResult loginResult) {
                        GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.i("result", "object :" + object.toString());
                                if (response.getError() != null) {

                                } else {
                                    Log.i("TAG", "user: " + object.toString());
                                    Log.i("TAG", "AccessToken: " + loginResult.getAccessToken().getToken());
//                            setResult(RESULT_OK);

                                    faceBooks = object.toString();
                                    faceLogin(object);

                                }

//                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                //intent에 페이스북 유저 정보를 담는다.
//                        String[] userString = new String[] {object.toString()};
//                        intent.putExtra("FacebookUser", object.toString());
//                        intent.putExtra("id", resultId.toString());
//                        startActivity(intent);
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





//        //SharedPreference
         pref = getSharedPreferences(sfValue, 0);
 //        editor = pref.edit();

        // 자동 로그인
        //if(pref.getBoolean("autoLogin", false)){
            id = pref.getString("Id","");
            pw = pref.getString("Pw","");
            facebook = pref.getString("Face","");
            google = pref.getString("google","");
            editId.setText(id);
            editPw.setText(pw);
//            autoLogin.setChecked(true);
            Log.i("왜 안되는거야 ", "왜 안되는거냐 페이스북아 :" + facebook);


        chId = editId.getText().toString();
        chPw = editPw.getText().toString();

       if (!facebook.equals("")){
//            Toast.makeText(MainActivity.this,"불일치합니다",Toast.LENGTH_SHORT).show();

            Intent intent = new Intent (LoginActivity.this,MainActivity.class);
            intent.putExtra("faceUser",facebook);
            startActivityForResult(intent, RESULT_OK);
            finish();
        }
            else if (!google.equals("")) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("GoogleUser", acct.getDisplayName().toString());
                intent.putExtra("GoogleEmail", acct.getEmail().toString());
                startActivityForResult(intent, RESULT_OK);
                finish();
            }



//        autoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked){
//                    Log.i("aaa", "ischecked : " + isChecked+"");
//                    String id = editId.getText().toString();
//                    String pw = editPw.getText().toString();
//                    String facebook = faceBooks.toString();
//                    editor.putString("Id", id);
//                    editor.putString("Pw", pw);
//                    editor.putString("Face",faceBooks);
//                    editor.putBoolean("autoLogin", true);
//
//                    editor.commit();
//
//
//
//
//                }else{
//                    Log.i("aaa", "ischecked : " + isChecked+"");
//                    editor.clear();
//                    editor.commit();
//
//                }
//            }
//        });
   }

    private void faceLogin(JSONObject object) {
        intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("faceUser", object.toString());
//                        intent.putExtra("id", resultId.toString());
        startActivityForResult(intent, RESULT_OK);
        finish();

    }


    //구글 로그인
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RESOLVE_CONNECTION_REQUEST_CODE);

    }

//    private boolean loginValidation(String id, String password) {
//        if(pref.getString("id","").equals(id) && pref.getString("pw","").equals(password)) {
//            // login success
//            return true;
//        } else if (pref.getString("id","").equals(null)){
//            // sign in first
//            Toast.makeText(this, "Please Sign in first", Toast.LENGTH_LONG).show();
//            return false;
//        } else {
//            // login failed
//            return false;
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        callbackManager.onActivityResult(requestCode, resultCode,data);

        switch ( requestCode ) {
            case RESOLVE_CONNECTION_REQUEST_CODE:
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent( data );
                if ( result.isSuccess( ) ) {
                    acct = result.getSignInAccount( );
                    // 계정 정보 얻어오기
                    Log.i( "google", acct.getDisplayName( ) );
                    Log.i( "google", acct.getEmail( ) );
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    //intent에 구글 유저 정보를 담는다.
                    //String[] userString = new String[] {acct.getDisplayName().toString()};
                    intent.putExtra("GoogleUser", acct.getDisplayName().toString());
                    //String[] userEmail = new String[]{acct.getEmail().toString()};
                    intent.putExtra("GoogleEmail", acct.getEmail().toString());
                    Log.i("google", "" +intent);
//                  intent.putExtra("id", resultId.toString());
                    startActivityForResult(intent,RESOLVE_CONNECTION_REQUEST_CODE);
                    finish();
                }
                     break;

                     default:
                         callbackManager.onActivityResult(requestCode, resultCode,data);
                }




    }


//
    @Override
    protected void onStop() {
            super.onStop();
            // SharedPreferences 에 설정값(특별히 기억해야할 사용자 값)을 저장하기
            pref = getSharedPreferences(sfValue, 0);
            SharedPreferences.Editor editor = pref.edit();//저장하려면 editor가 필요
            String id = editId.getText().toString(); // 사용자가 입력한 값
            String pw = editPw.getText().toString();

            editor.putString("Id", id); // 입력
            editor.putString("Pw", pw); // 입력
            editor.putString("Face", faceBooks);

            editor.commit(); // 파일에 최종 반영함
        }
        // Shared저장완료
}
