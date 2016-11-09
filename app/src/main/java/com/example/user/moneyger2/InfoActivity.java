package com.example.user.moneyger2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.example.user.moneyger2.adapter.InfoActAdapter;
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

        ImageButton info_act_send_btn = (ImageButton)findViewById(R.id.info_act_send_btn);

        info_act_send_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("smsto:01022865413");
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra("sms_body","[MONEYGER]\n2016.05.17\nEOS 정기회합 비용\n10000원\n김나용 신한 110438358091\n으로 입금해주세요.^^");
                startActivity(intent);
            }
        });


    }

    public ArrayList<InfoActData> getInfoActList(){
        info_actList.add(new InfoActData(false,"김나용","12790원"));
        info_actList.add(new InfoActData(false,"김정욱","12790원"));
        info_actList.add(new InfoActData(false,"김나용","190000원"));

        return info_actList;
    }
}
