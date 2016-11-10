package com.example.user.moneyger2;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.moneyger2.adapter.InfoActAdapter;
import com.example.user.moneyger2.data.InfoActData;
import com.example.user.moneyger2.dbsql.MySQLOpenHelper;

import java.util.ArrayList;

/**
 * Created by User on 2016-11-07.
 */
public class InfoActivity extends Activity {
    SQLiteDatabase db;
    MySQLOpenHelper helper;
    private final static String TABLE_NAME = "debtlist";

    private String gathering;

    private TextView title;

    private RecyclerView info_actView;
    private ArrayList<InfoActData> info_actList = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent gi = getIntent();
        gathering = gi.getStringExtra("location");

        title = (TextView) findViewById(R.id.info_act_title);
        info_actView = (RecyclerView) findViewById(R.id.info_act_list);

        title.setText(gathering);

        // RecyclerView 에 LinearLayoutManager를 셋팅
        info_actView.setLayoutManager(new LinearLayoutManager(InfoActivity.this));
        // 만들어 둔 IntentAdapter를 RecyclerView에 셋팅
        info_actView.setAdapter(new InfoActAdapter(InfoActivity.this, getInfoActList()));

        final ImageButton info_act_send_btn = (ImageButton) findViewById(R.id.info_act_send_btn);

        info_act_send_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                SharedPreferences SP = getSharedPreferences("", 0);
                String strName = SP.getString("name", "");
                String strBank = SP.getString("bank", "");
                String strAccount = SP.getString("account", "");

                int intPh_num = 0;
                String Debt = "";
                String Date = "";
                String strPh_num = null;

                for (int i = 0; i < info_actList.size(); i++) {
                    if (info_actList.get(i).isCheck_state() == true) {
                        // 정보 불러오기
                        strPh_num = info_actList.get(i).getPh_num();
                        Date += info_actList.get(i).getMonth() + "." + info_actList.get(i).getDay();
                        Debt = info_actList.get(i).getDebt();

                        // SMS관련
                        String SENT = "SMS_SENT";
                        String DELIVERED = "SMS_DELIVERED";

                        PendingIntent sentPI = PendingIntent.getBroadcast(getBaseContext(), 0, new Intent(SENT), 0);
                        PendingIntent deliveredPI = PendingIntent.getBroadcast(getBaseContext(), 0, new Intent(DELIVERED), 0);

                        registerReceiver(new BroadcastReceiver() {
                            @Override
                            public void onReceive(Context context, Intent arg1) {
                                switch (getResultCode()) {
                                    case Activity.RESULT_OK:
                                        Toast.makeText(getBaseContext(), "문자 전송을 완료하였습니다.", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }
                        }, new IntentFilter(SENT));

                        SmsManager smsManager = android.telephony.SmsManager.getDefault();
                        smsManager.sendTextMessage(strPh_num, null, Date+"\n"+title.getText()+"\n"+Debt+"\n"+strBank + " " + strAccount + "로\n" + "입금해주세요.^^\n", null, null);
                    }
                }
            }
        });


    }

    public ArrayList<InfoActData> getInfoActList() {

        helper = new MySQLOpenHelper(this);//헬퍼를 사용하여
        try {
            db = helper.getWritableDatabase();//DB를 쓰기가능으로 연다.
        } catch (SQLiteException e) {
            db = helper.getReadableDatabase();//에러 시 읽기전용으로.
        }

        Cursor csr = db.query(TABLE_NAME, null, "gathering=?", new String[]{gathering}, null, null, null);

        while (csr.moveToNext()) {//커서를 처음레코드부터 마지막레코드까지 이동하며 반복.//
            info_actList.add(new InfoActData(false, csr.getString(1), csr.getString(3) + "원", csr.getString(csr.getColumnIndex("phonenum")), csr.getInt(csr.getColumnIndex("year")), csr.getInt(csr.getColumnIndex("month")), csr.getInt(csr.getColumnIndex("day"))));
        }
        ;
        csr.close();

        return info_actList;
    }
}
