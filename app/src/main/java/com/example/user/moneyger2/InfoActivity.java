package com.example.user.moneyger2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.moneyger2.adapter.CalResAdapter;
import com.example.user.moneyger2.adapter.InfoActAdapter;
import com.example.user.moneyger2.data.CalResData;
import com.example.user.moneyger2.data.InfoActData;

import java.util.ArrayList;

/**
 * Created by User on 2016-11-07.
 */
public class InfoActivity extends Activity {
    private RecyclerView info_actView;
    private ArrayList<InfoActData> info_actList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        info_actView = (RecyclerView)findViewById(R.id.info_act_list);

        // RecyclerView 에 LinearLayoutManager를 셋팅
        info_actView.setLayoutManager(new LinearLayoutManager(InfoActivity.this));
        // 만들어 둔 IntentAdapter를 RecyclerView에 셋팅
        info_actView.setAdapter(new InfoActAdapter(InfoActivity.this, getInfoActList()));
    }

    public ArrayList<InfoActData> getInfoActList(){
        info_actList.add(new InfoActData(false,"김나용","100원"));
        return info_actList;
    }
}
