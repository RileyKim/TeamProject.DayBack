package com.taeksukim.android.dayback;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.taeksukim.android.daybacklogin.R;






public class MemoActivity extends AppCompatActivity {



    ImageView memoCancel, memoSave, tongue_out, happy, soso, sad, angry;
    TextView textDate;
    EditText editTitle, editContent;
    IconizedMenu popup;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        happy = (ImageView) findViewById(R.id.happy);
        soso = (ImageView) findViewById(R.id.soso);
        sad = (ImageView) findViewById(R.id.sad);
        angry = (ImageView) findViewById(R.id.angry);


        // memo date
        Data data = new Data();
        textDate = (TextView) findViewById(R.id.textDate);
        textDate.setText(data.strToday);


        // 메모 취소버튼 클릭시 알림창.
        memoCancel = (ImageView) findViewById(R.id.memoCancel);
        memoCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MemoActivity.this);
                //제목 세팅
                alertDialogBuilder.setTitle("확인");
                alertDialogBuilder.setMessage("작성을 취소하겠습니까?");

                alertDialogBuilder
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialogBuilder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MemoActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        });
                AlertDialog dialog = alertDialogBuilder.create();
                dialog.show();
            }

        });


        // 메모 저장버튼 클릭 시 알림창.
        memoSave = (ImageView) findViewById(R.id.memoSave);
        memoSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MemoActivity.this);
                //제목 세팅
                alertDialogBuilder.setTitle("확인");
                alertDialogBuilder.setMessage("작성을 저장하겠습니까?");

                alertDialogBuilder
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialogBuilder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO
                        // 메모 작성한 부분 저장해서 메인 페이지 리사이클러뷰에 보여주어야한다.
                        Intent intent = new Intent(MemoActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                AlertDialog dialog = alertDialogBuilder.create();
                dialog.show();
            }

        });

        // 이미지버튼 이모티콘메뉴
        tongue_out = (ImageView) findViewById(R.id.tongue_out);
        tongue_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.tongue_out:
                            popup = new IconizedMenu(MemoActivity.this, v);
                            popup.inflate(R.menu.emotion_menu);
                            popup.setOnMenuItemClickListener(new IconizedMenu.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem item) {
                                    switch (item.getItemId()){
                                        case R.id.happy:
                                            tongue_out.setVisibility(View.GONE);
                                            happy.setVisibility(View.VISIBLE);
                                            soso.setVisibility(View.GONE);
                                            sad.setVisibility(View.GONE);
                                            angry.setVisibility(View.GONE);
                                            Toast.makeText(MemoActivity.this, "행복해요ㅎ", Toast.LENGTH_SHORT).show();
                                            break;

                                        case R.id.soso :
                                            tongue_out.setVisibility(View.GONE);
                                            happy.setVisibility(View.GONE);
                                            soso.setVisibility(View.VISIBLE);
                                            sad.setVisibility(View.GONE);
                                            angry.setVisibility(View.GONE);
                                            Toast.makeText(MemoActivity.this, "그냥 그래요ㅋ", Toast.LENGTH_SHORT).show();
                                            break;

                                        case R.id.sad :
                                            tongue_out.setVisibility(View.GONE);
                                            happy.setVisibility(View.GONE);
                                            soso.setVisibility(View.GONE);
                                            sad.setVisibility(View.VISIBLE);
                                            angry.setVisibility(View.GONE);
                                            Toast.makeText(MemoActivity.this, "슬프네요..", Toast.LENGTH_SHORT).show();
                                            break;

                                        case R.id.angry :
                                            tongue_out.setVisibility(View.GONE);
                                            happy.setVisibility(View.GONE);
                                            soso.setVisibility(View.GONE);
                                            sad.setVisibility(View.GONE);
                                            angry.setVisibility(View.VISIBLE);
                                            Toast.makeText(MemoActivity.this, "짜증난다!!", Toast.LENGTH_SHORT).show();
                                            break;
                                    }
                                    return false;
                                }
                            });
                            popup.show();




                    }
            }

        });

        happy = (ImageView) findViewById(R.id.happy);
        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.happy:
                        popup = new IconizedMenu(MemoActivity.this, v);
                        popup.inflate(R.menu.emotion_menu);
                        popup.setOnMenuItemClickListener(new IconizedMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()){
                                    case R.id.happy:
                                        tongue_out.setVisibility(View.GONE);
                                        happy.setVisibility(View.VISIBLE);
                                        soso.setVisibility(View.GONE);
                                        sad.setVisibility(View.GONE);
                                        angry.setVisibility(View.GONE);
                                        Toast.makeText(MemoActivity.this, "행복해요ㅎ", Toast.LENGTH_SHORT).show();
                                        break;

                                    case R.id.soso :
                                        tongue_out.setVisibility(View.GONE);
                                        happy.setVisibility(View.GONE);
                                        soso.setVisibility(View.VISIBLE);
                                        sad.setVisibility(View.GONE);
                                        angry.setVisibility(View.GONE);
                                        Toast.makeText(MemoActivity.this, "그냥 그래요ㅋ", Toast.LENGTH_SHORT).show();
                                        break;

                                    case R.id.sad :
                                        tongue_out.setVisibility(View.GONE);
                                        happy.setVisibility(View.GONE);
                                        soso.setVisibility(View.GONE);
                                        sad.setVisibility(View.VISIBLE);
                                        angry.setVisibility(View.GONE);
                                        Toast.makeText(MemoActivity.this, "슬프네요..", Toast.LENGTH_SHORT).show();
                                        break;

                                    case R.id.angry :
                                        tongue_out.setVisibility(View.GONE);
                                        happy.setVisibility(View.GONE);
                                        soso.setVisibility(View.GONE);
                                        sad.setVisibility(View.GONE);
                                        angry.setVisibility(View.VISIBLE);
                                        Toast.makeText(MemoActivity.this, "짜증난다!!", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                                return false;
                            }
                        });
                        popup.show();




                }
            }

        });

        soso = (ImageView) findViewById(R.id.soso);
        soso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.soso:
                        popup = new IconizedMenu(MemoActivity.this, v);
                        popup.inflate(R.menu.emotion_menu);
                        popup.setOnMenuItemClickListener(new IconizedMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()){
                                    case R.id.happy:
                                        tongue_out.setVisibility(View.GONE);
                                        happy.setVisibility(View.VISIBLE);
                                        soso.setVisibility(View.GONE);
                                        sad.setVisibility(View.GONE);
                                        angry.setVisibility(View.GONE);
                                        Toast.makeText(MemoActivity.this, "행복해요ㅎ", Toast.LENGTH_SHORT).show();
                                        break;

                                    case R.id.soso :
                                        tongue_out.setVisibility(View.GONE);
                                        happy.setVisibility(View.GONE);
                                        soso.setVisibility(View.VISIBLE);
                                        sad.setVisibility(View.GONE);
                                        angry.setVisibility(View.GONE);
                                        Toast.makeText(MemoActivity.this, "그냥 그래요ㅋ", Toast.LENGTH_SHORT).show();
                                        break;

                                    case R.id.sad :
                                        tongue_out.setVisibility(View.GONE);
                                        happy.setVisibility(View.GONE);
                                        soso.setVisibility(View.GONE);
                                        sad.setVisibility(View.VISIBLE);
                                        angry.setVisibility(View.GONE);
                                        Toast.makeText(MemoActivity.this, "슬프네요..", Toast.LENGTH_SHORT).show();
                                        break;

                                    case R.id.angry :
                                        tongue_out.setVisibility(View.GONE);
                                        happy.setVisibility(View.GONE);
                                        soso.setVisibility(View.GONE);
                                        sad.setVisibility(View.GONE);
                                        angry.setVisibility(View.VISIBLE);
                                        Toast.makeText(MemoActivity.this, "짜증난다!!", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                                return false;
                            }
                        });
                        popup.show();




                }
            }

        });

        sad = (ImageView) findViewById(R.id.sad);
        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sad:
                        popup = new IconizedMenu(MemoActivity.this, v);
                        popup.inflate(R.menu.emotion_menu);
                        popup.setOnMenuItemClickListener(new IconizedMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()){
                                    case R.id.happy:
                                        tongue_out.setVisibility(View.GONE);
                                        happy.setVisibility(View.VISIBLE);
                                        soso.setVisibility(View.GONE);
                                        sad.setVisibility(View.GONE);
                                        angry.setVisibility(View.GONE);
                                        Toast.makeText(MemoActivity.this, "행복해요ㅎ", Toast.LENGTH_SHORT).show();
                                        break;

                                    case R.id.soso :
                                        tongue_out.setVisibility(View.GONE);
                                        happy.setVisibility(View.GONE);
                                        soso.setVisibility(View.VISIBLE);
                                        sad.setVisibility(View.GONE);
                                        angry.setVisibility(View.GONE);
                                        Toast.makeText(MemoActivity.this, "그냥 그래요ㅋ", Toast.LENGTH_SHORT).show();
                                        break;

                                    case R.id.sad :
                                        tongue_out.setVisibility(View.GONE);
                                        happy.setVisibility(View.GONE);
                                        soso.setVisibility(View.GONE);
                                        sad.setVisibility(View.VISIBLE);
                                        angry.setVisibility(View.GONE);
                                        Toast.makeText(MemoActivity.this, "슬프네요..", Toast.LENGTH_SHORT).show();
                                        break;

                                    case R.id.angry :
                                        tongue_out.setVisibility(View.GONE);
                                        happy.setVisibility(View.GONE);
                                        soso.setVisibility(View.GONE);
                                        sad.setVisibility(View.GONE);
                                        angry.setVisibility(View.VISIBLE);
                                        Toast.makeText(MemoActivity.this, "짜증난다!!", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                                return false;
                            }
                        });
                        popup.show();




                }
            }

        });

        angry = (ImageView) findViewById(R.id.angry);
        angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.angry:
                        popup = new IconizedMenu(MemoActivity.this, v);
                        popup.inflate(R.menu.emotion_menu);
                        popup.setOnMenuItemClickListener(new IconizedMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()){
                                    case R.id.happy:
                                        tongue_out.setVisibility(View.GONE);
                                        happy.setVisibility(View.VISIBLE);
                                        soso.setVisibility(View.GONE);
                                        sad.setVisibility(View.GONE);
                                        angry.setVisibility(View.GONE);
                                        Toast.makeText(MemoActivity.this, "행복해요ㅎ", Toast.LENGTH_SHORT).show();
                                        break;

                                    case R.id.soso :
                                        tongue_out.setVisibility(View.GONE);
                                        happy.setVisibility(View.GONE);
                                        soso.setVisibility(View.VISIBLE);
                                        sad.setVisibility(View.GONE);
                                        angry.setVisibility(View.GONE);
                                        Toast.makeText(MemoActivity.this, "그냥 그래요ㅋ", Toast.LENGTH_SHORT).show();
                                        break;

                                    case R.id.sad :
                                        tongue_out.setVisibility(View.GONE);
                                        happy.setVisibility(View.GONE);
                                        soso.setVisibility(View.GONE);
                                        sad.setVisibility(View.VISIBLE);
                                        angry.setVisibility(View.GONE);
                                        Toast.makeText(MemoActivity.this, "슬프네요..", Toast.LENGTH_SHORT).show();
                                        break;

                                    case R.id.angry :
                                        tongue_out.setVisibility(View.GONE);
                                        happy.setVisibility(View.GONE);
                                        soso.setVisibility(View.GONE);
                                        sad.setVisibility(View.GONE);
                                        angry.setVisibility(View.VISIBLE);
                                        Toast.makeText(MemoActivity.this, "짜증난다!!", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                                return false;
                            }
                        });
                        popup.show();




                }
            }

        });

    }







}
