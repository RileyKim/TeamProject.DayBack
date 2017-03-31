package com.taeksukim.android.dayback;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.taeksukim.android.daybacklogin.R;

import static android.R.attr.data;

public class MemoActivity extends AppCompatActivity {

    ImageView cancel, save;
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


    }
}
