package com.example.user.moneyger2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.moneyger2.data.SearchData;
import com.example.user.moneyger2.dbsql.DBManager;

/**
 * Created by nayon on 2016-11-10.
 */
public class SearchCheckActivity extends Activity{
    private String name,ph_num,debt;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.search_check);
        Button search_check_btn = (Button)findViewById(R.id.search_check_btn);
        final EditText minus_debt = (EditText)findViewById(R.id.search_check_edit);

        Intent gi = getIntent();

        name = gi.getStringExtra("NAME");
        ph_num = gi.getStringExtra("PHNUM");
        debt = gi.getStringExtra("DEBT");


        search_check_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new DBManager(v.getContext()).update(new SearchData(false,name,ph_num,debt),Integer.parseInt(minus_debt.getText().toString()));
                Intent intent = new Intent(SearchCheckActivity.this, MainActivity.class);
                intent.putExtra("fragnum",3);
                startActivity(intent);
                finish();
            }
        });

    }


}
