package com.example.user.moneyger2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;


import com.example.user.moneyger2.adapter.CalOriginAdapter;
import com.example.user.moneyger2.adapter.CalResAdapter;
import com.example.user.moneyger2.data.CalOriginData;
import com.example.user.moneyger2.data.CalResData;

import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by User on 2016-11-07.
 */
public class CalResultListActivity extends Activity{
    private ImageButton cal_res_btn;

    private RecyclerView cal_resView;
    private ArrayList<CalResData> cal_resList = new ArrayList<>();

    private int total, n;
    private String[] ph_num = new String[1000];
    private String[] names = new String[1000];
    private int[] result;
    private int SOcheck;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal_result_list);

        Intent gi = getIntent();
        total = gi.getIntExtra("total_money",0);
        n = gi.getIntExtra("total_person",1);
        SOcheck = gi.getIntExtra("SOcheck",1);
        result = new int[n];
        if(SOcheck == 1){
            for(int i=0;i<n;i++){
                names[i] = (i+1)+"";
            }
        }
        else if(SOcheck == 2){
            for(int i=0;i<n;i++){
                names[i] = CalOriginActivity.checkedList.get(i).getName();
            }
        }
        calculate();

        cal_resView = (RecyclerView)findViewById(R.id.cal_result_view);

        // RecyclerView 에 LinearLayoutManager를 셋팅
        cal_resView.setLayoutManager(new LinearLayoutManager(CalResultListActivity.this));
        // 만들어 둔 IntentAdapter를 RecyclerView에 셋팅
        cal_resView.setAdapter(new CalResAdapter(CalResultListActivity.this, getCalResView()));

        cal_res_btn = (ImageButton)findViewById(R.id.cal_result_save_btn);
        cal_res_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalResultListActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public ArrayList<CalResData> getCalResView(){
        for(int i=0;i<n;i++){
            cal_resList.add(new CalResData(names[i],result[i]+"원"));
        }
        return cal_resList;
    }
    public void calculate(){
        int[] ch = new int[n];
        int ran;
        int rest=0;
        Random random = new Random();

        for(int i=0;i<n;i++) result[i] = 0;
        if (total % 1000 != 0) {
            ran = random.nextInt(n);

            rest = total%1000;
            ch[ran] = 2;
        }
        total /= 1000;

        for (int i = 0; i < n;i++) result[i] += total / n;
       total %= n;
        while(total > 0){
            ran = random.nextInt(n);
            if (ch[ran] != 0) continue;
            result[ran] ++;
            total--;
        }

        for (int i = 0; i < n; i++) {
            result[i] *= 1000;
            if (ch[i] == 2) result[i] += rest;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(CalResultListActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
