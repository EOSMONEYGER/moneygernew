package com.example.user.moneyger2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.moneyger2.adapter.CalOriginAdapter;
import com.example.user.moneyger2.adapter.InfoAdapter;
import com.example.user.moneyger2.data.CalOriginData;
import com.example.user.moneyger2.data.InfoData;

import java.util.ArrayList;

/**
 * Created by User on 2016-11-07.
 */
public class CalOriginActivity extends Activity{
    private RecyclerView cal_originView;
    private ArrayList<CalOriginData> cal_originList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal_origin);

        cal_originView = (RecyclerView)findViewById(R.id.cal_addressbook_view);

        // RecyclerView 에 LinearLayoutManager를 셋팅
        cal_originView.setLayoutManager(new LinearLayoutManager(CalOriginActivity.this));
        // 만들어 둔 IntentAdapter를 RecyclerView에 셋팅
        cal_originView.setAdapter(new CalOriginAdapter(CalOriginActivity.this, getCal_originList()));

    }
    public ArrayList<CalOriginData> getCal_originList() {
        if (cal_originList.size() == 0) {
            cal_originList.add(new CalOriginData(false,"김도현","010101010"));
        }

        return cal_originList;
    }
}
