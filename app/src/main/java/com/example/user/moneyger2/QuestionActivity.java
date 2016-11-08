package com.example.user.moneyger2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

/**
 * Created by nayon on 2016-11-09.
 */
public class QuestionActivity extends Activity{

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
    }

    public void onClickQ(View v){
        Intent intent;
        Uri uri;

        switch (v.getId()){
            case R.id.question_info :
                intent = new Intent(QuestionActivity.this,QuestionInfoActivity.class);
                startActivity(intent);
                break;


            case R.id.question_email :
                uri = Uri.parse("mailto:na_yong517@naver.com");
                intent = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(intent);
                break;

            case R.id.question_message :
                uri = Uri.parse("smsto:01022865413");
                intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra("sms_body","[MONEYGER_문의사항]\n");
                startActivity(intent);
                break;

        }


    }



}
