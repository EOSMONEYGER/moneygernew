package com.example.user.moneyger2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.user.moneyger2.data.SearchData;
import com.example.user.moneyger2.dbsql.DBManager;

/**
 * Created by nayon on 2016-11-10.
 */
public class SearchCheckActivity extends Activity{
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.search_check);
        Button search_check_btn = (Button)findViewById(R.id.search_check_btn);

        search_check_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new DBManager(v.getContext()).update(new SearchData(false,search.getName(),search.getPh_num(),search.getDebt()),10000);
            }
        });

    }


}
