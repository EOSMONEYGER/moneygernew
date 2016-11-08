package com.example.user.moneyger2;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by User on 2016-11-07.
 */
public class CalSimpleActivity extends Activity{
    private Button resultBtn;
    private EditText total_person;
    public static int Total_person=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.cal_simple);

        Intent gi = getIntent();

        final int Total_money = gi.getIntExtra("total_money", 0);

        total_person = (EditText)findViewById(R.id.cal_total_person_num);
        total_person.setText("");

        resultBtn = (Button)findViewById(R.id.cal_simple_next_btn);

        resultBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(total_person.getText().toString().length() == 0  || total_person.getText().toString().equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(), "명수를 입력해주세요", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                Total_person = 0;
                Total_person = Integer.parseInt(total_person.getText().toString());
                Intent intent = new Intent(CalSimpleActivity.this, CalResultListActivity.class);
                intent.putExtra("SOcheck",1);
                intent.putExtra("total_person", Total_person);
                intent.putExtra("total_money",Total_money);
                startActivity(intent);
            }
        });
    }


}
