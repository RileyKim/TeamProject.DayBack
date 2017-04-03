package com.taeksukim.android.dayback;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.taeksukim.android.daybacklogin.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static java.security.AccessController.getContext;


public class MemoActivity extends AppCompatActivity {

    Context context;

    ImageView memoCancel, memoSave, tongue_out;
    TextView textDate;
    EditText editTitle, editContent;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);


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
//                            PopupMenu popUp = new PopupMenu(MemoActivity.this, v );
//                            popUp.inflate(R.menu.emotion_menu);
//                            getMenuInflater().inflate(R.menu.emotion_menu, popUp.getMenu());
//                            popUp.setOnMenuItemClickListener(listener);
//
//                            //MenuPopupHelper menuHelper  = new MenuPopupHelper(Context context,);
//
//                            popUp.show();
                            IconizedMenu popup = new IconizedMenu(MemoActivity.this, v);
                            popup.inflate(R.menu.emotion_menu);
                            popup.show();


                    }
            }

        });

    }

    PopupMenu.OnMenuItemClickListener listener = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.tongue_out :
                    Toast.makeText(MemoActivity.this, "tongue", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;

        }
    };


}
