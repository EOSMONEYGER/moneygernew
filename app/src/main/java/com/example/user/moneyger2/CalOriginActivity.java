package com.example.user.moneyger2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.moneyger2.adapter.CalOriginAdapter;
import com.example.user.moneyger2.adapter.InfoAdapter;
import com.example.user.moneyger2.data.CalOriginData;
import com.example.user.moneyger2.data.InfoData;

import java.util.ArrayList;

/**
 * Created by User on 2016-11-07.
 */
public class CalOriginActivity extends Activity{
    private Button cal_origin_btn;
    private RecyclerView cal_originView;
    private EditText cal_origin_edit;
    private ArrayList<CalOriginData> cal_originList = new ArrayList<>();
    public static ArrayList<CalOriginData> checkedList = new ArrayList<>();
    public static ArrayList<CalOriginData> searchList = new ArrayList<>();

    int totalMoney = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal_origin);

        Intent intent = getIntent();
        totalMoney = intent.getIntExtra("total_money",0);
        checkedList.clear();

        cal_originView = (RecyclerView)findViewById(R.id.cal_addressbook_view);
        cal_origin_edit = (EditText)findViewById(R.id.cal_origin_search_edit);

        // RecyclerView 에 LinearLayoutManager를 셋팅
        cal_originView.setLayoutManager(new LinearLayoutManager(CalOriginActivity.this));
        // 만들어 둔 IntentAdapter를 RecyclerView에 셋팅
        cal_originView.setAdapter(new CalOriginAdapter(CalOriginActivity.this, getCal_originList()));

        cal_origin_btn = (Button)findViewById(R.id.cal_origin_btn);
        cal_origin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i = 0; i < cal_originList.size(); i++) {
                    if(cal_originList.get(i).isCheck_state()) {
                        checkedList.add(cal_originList.get(i));
                    }
                }

                Intent intent = new Intent(CalOriginActivity.this, CalResultListActivity.class);
                intent.putExtra("SOcheck",2);
                intent.putExtra("total_money", totalMoney);
                intent.putExtra("total_person",checkedList.size());
                startActivity(intent);
                finish();
            }
        });

        cal_origin_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                /*String s = charSequence.toString();

                if(s.length() == 0) {
                    cal_originView.setAdapter(new CalOriginAdapter(CalOriginActivity.this, cal_originList));
                } else {
                    for (int j = 0; j < cal_originList.size(); j++) {
                        if (cal_originList.get(j).getName().contains(s)) {
                            searchList.add(cal_originList.get(j));
                        }
                    }
                    cal_originView.setAdapter(new CalOriginAdapter(CalOriginActivity.this, searchList));
                }
*/
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = editable.toString();

                searchList.clear();

                if(s.length() == 0) {
                    cal_originView.setAdapter(new CalOriginAdapter(CalOriginActivity.this, cal_originList));
                } else {
                    for (int j = 0; j < cal_originList.size(); j++) {
                        if (cal_originList.get(j).getName().contains(s)) {
                            searchList.add(cal_originList.get(j));
                        }
                    }
                    cal_originView.setAdapter(new CalOriginAdapter(CalOriginActivity.this, searchList));
                }

            }
        });
    }
    public ArrayList<CalOriginData> getCal_originList() {

        String [] arrProjection = {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME
        };
        String [] arrPhoneProjection = {
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };
        Cursor clsCursor = getApplicationContext().getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                arrProjection,
                ContactsContract.Contacts.HAS_PHONE_NUMBER + "=1",
                null, null
        );
        while(clsCursor.moveToNext()){
            String strContactId = clsCursor.getString(0);
            //폰번호
            Cursor clsPhoneCursor = getApplicationContext().getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    arrPhoneProjection,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + strContactId,
                    null, null
            );
            String ph_num ="";
            while(clsPhoneCursor.moveToNext()){
                ph_num += clsPhoneCursor.getString(0);
            }
            clsPhoneCursor.close();

            cal_originList.add(new CalOriginData(false,clsCursor.getString(1),ph_num));
        }
        clsCursor.close();

        return cal_originList;
    }
}
