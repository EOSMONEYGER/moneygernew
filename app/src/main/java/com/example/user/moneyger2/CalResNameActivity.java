package com.example.user.moneyger2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by User on 2016-11-09.
 */
public class CalResNameActivity extends Activity{
    private ImageButton save_btn;
    private EditText gathering;

    private String[] ph_num;
    private String[] names;
    private int[] result;
    private int total_person;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal_result_name);

        Intent gi = getIntent();
        total_person = gi.getIntExtra("total_person",0);
        ph_num = new String[total_person];
        names = new String[total_person];
        result = new int[total_person];

        ph_num = gi.getStringArrayExtra("ph_num");
        names = gi.getStringArrayExtra("names");
        result = gi.getIntArrayExtra("result");

        gathering = (EditText)findViewById(R.id.cal_result_name);

        save_btn = (ImageButton)findViewById(R.id.cal_result_save_btn);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalResNameActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
