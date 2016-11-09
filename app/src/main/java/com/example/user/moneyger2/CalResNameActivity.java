package com.example.user.moneyger2;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.user.moneyger2.DBsql.MySQLOpenHelper;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by User on 2016-11-09.
 */
public class CalResNameActivity extends Activity{
    SQLiteDatabase db;
    MySQLOpenHelper helper;
    private final static String TABLE_NAME = "debtlist";

    private ImageButton save_btn;
    private EditText gathering;

    private String[] ph_num;
    private String[] names;
    private int[] result;
    private int total_person;

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal_result_name);

        helper = new MySQLOpenHelper(this);//헬퍼를 사용하여
        try{
            db = helper.getWritableDatabase();//DB를 쓰기가능으로 연다.
        } catch(SQLiteException e){
            db = helper.getReadableDatabase();//에러 시 읽기전용으로.
        }

        Intent gi = getIntent();
        total_person = gi.getIntExtra("total_person",0);
       /* ph_num = new String[total_person];
        names = new String[total_person];
        result = new int[total_person];*/

        ph_num = gi.getStringArrayExtra("ph_num");
        names = gi.getStringArrayExtra("names");
        result = gi.getIntArrayExtra("result");

        gathering = (EditText)findViewById(R.id.cal_result_name);

        save_btn = (ImageButton)findViewById(R.id.cal_result_save_btn);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar date = Calendar.getInstance();
                int year = date.get(Calendar.YEAR);
                int month = date.get(Calendar.MONTH);
                int day = date.get(Calendar.DATE);

                for(int i=1;i<total_person;i++){
                    ContentValues cv = new ContentValues();
                    cv.put("name", names[i]);
                    cv.put("phonenum", ph_num[i]);
                    cv.put("debt",result[i]);
                    cv.put("gathering",gathering.getText().toString());
                    cv.put("year",year);
                    cv.put("month",month);
                    cv.put("day", day);
                    db.insert(TABLE_NAME,null,cv);
                }

                Intent intent = new Intent(CalResNameActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
