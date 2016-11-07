package com.example.user.moneyger2;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by User on 2016-11-07.
 */
public class CalSimpleActivity extends Activity{
    private Button resultBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal_simple);

        resultBtn = (Button)findViewById(R.id.cal_simple_next_btn);

        resultBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(CalSimpleActivity.this, CalSimpleActivity.class);
                startActivity(intent);
            }
        });
    }


}
