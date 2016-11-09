package com.example.user.moneyger2;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by User on 2016-11-07.
 */
public class SettingActivity extends Activity {

    EditText name_edit, bank_edit, account_edit;
    Button name_btn, bank_btn, account_btn;
    String strName = "", strBank = "", strAccount = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        name_edit = (EditText) findViewById(R.id.setting_name);
        bank_edit = (EditText) findViewById(R.id.setting_bank);
        account_edit = (EditText) findViewById(R.id.setting_account);
        Button setting_save = (Button) findViewById(R.id.setting_save);

        SharedPreferences SP = getSharedPreferences(strName, 0);
        String str = SP.getString("name", "");
        name_edit.setText(str);

        SP = getSharedPreferences(strBank,0);
        str = SP.getString("bank","");
        bank_edit.setText(str);

        SP = getSharedPreferences(strAccount,0);
        str = SP.getString("account","");
        account_edit.setText(str);

        setting_save.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                SharedPreferences SP = getSharedPreferences(strName, 0);
                SharedPreferences.Editor editor = SP.edit();
                String str = name_edit.getText().toString();
                editor.putString("name", str);
                editor.commit();

                SP = getSharedPreferences(strBank,0);
                editor = SP.edit();
                str = bank_edit.getText().toString();
                editor.putString("bank",str);
                editor.commit();

                SP = getSharedPreferences(strAccount,0);
                editor = SP.edit();
                str = account_edit.getText().toString();
                editor.putString("account",str);
                editor.commit();

                Toast.makeText(getBaseContext(), "내 정보를 저장하였습니다.", Toast.LENGTH_SHORT).show();

                onBackPressed();

            }

        });
    }


}
