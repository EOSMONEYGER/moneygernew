package com.example.user.moneyger2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.moneyger2.data.SearchData;
import com.example.user.moneyger2.dbsql.DBManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button infoBtn, calBtn, rankingBtn, searchBtn, settingBtn, questionBtn;

    private FragmentManager fm;

    private final int FRAGMENT_CAL = 0;
    private final int FRAGMENT_INFO = 1;
    private final int FRAGMENT_RANKING = 2;
    private final int FRAGMENT_SEARCH = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(SplashActivity.check == 0) {
            startActivity(new Intent(this, SplashActivity.class));
            SplashActivity.check=1;
        }
        setContentView(R.layout.activity_main);

        // FragmentManager 객체 초기화
        fm = getSupportFragmentManager();

        calBtn = (Button)findViewById(R.id.main_cal_btn);
        infoBtn = (Button)findViewById(R.id.main_info_btn);
        searchBtn = (Button)findViewById(R.id.main_search_btn);
        rankingBtn = (Button)findViewById(R.id.main_ranking_btn);
        settingBtn = (Button)findViewById(R.id.main_setting_btn) ;
        questionBtn = (Button)findViewById(R.id.main_question_btn);

        calBtn.setOnClickListener(this);
        infoBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);
        rankingBtn.setOnClickListener(this);
        settingBtn.setOnClickListener(this);
        questionBtn.setOnClickListener(this);

        replaceFragment(FRAGMENT_CAL);
    }

    private void replaceFragment(int position) {
        FragmentTransaction ft = fm.beginTransaction();

        if (position == FRAGMENT_CAL) {
            /** 각 버튼의 Selected state 를 업데이트 **/
            calBtn.setSelected(true);
            infoBtn.setSelected(false);
            searchBtn.setSelected(false);
            rankingBtn.setSelected(false);
            // Fragment switching
            ft.replace(R.id.tab_fragment, new CalFragment());
        } else if(position == FRAGMENT_INFO){
            /** 각 버튼의 Selected state 를 업데이트 **/
            calBtn.setSelected(false);
            infoBtn.setSelected(true);
            searchBtn.setSelected(false);
            rankingBtn.setSelected(false);
            // Fragment switching
            ft.replace(R.id.tab_fragment, new InfoFragment());
        } else if(position == FRAGMENT_SEARCH){
            /** 각 버튼의 Selected state 를 업데이트 **/
            calBtn.setSelected(false);
            infoBtn.setSelected(false);
            searchBtn.setSelected(true);
            rankingBtn.setSelected(false);
            // Fragment switching
            ft.replace(R.id.tab_fragment, new SearchFragment());
        } else if(position == FRAGMENT_RANKING) {
            /** 각 버튼의 Selected state 를 업데이트 **/
            calBtn.setSelected(false);
            infoBtn.setSelected(false);
            searchBtn.setSelected(false);
            rankingBtn.setSelected(true);
            // Fragment switching
            ft.replace(R.id.tab_fragment, new RankingFragment());
        }

        // 변경사항 적용
        ft.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_cal_btn:
                replaceFragment(FRAGMENT_CAL);
                break;
            case R.id.main_info_btn:
                replaceFragment(FRAGMENT_INFO);
                break;
            case R.id.main_search_btn:
                replaceFragment(FRAGMENT_SEARCH);
                break;
            case R.id.main_ranking_btn:
                replaceFragment(FRAGMENT_RANKING);
                break;
            case R.id.main_setting_btn:
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.main_question_btn:
                Intent intent2 = new Intent(MainActivity.this, QuestionActivity.class);
                startActivity(intent2);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        //Toast.makeText(getApplicationContext(), "어플리케이션을 종료합니다",Toast.LENGTH_LONG).show();

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);     // 여기서 this는 Activity의 this

        // 여기서 부터는 알림창의 속성 설정
        builder.setTitle("종료")        // 제목 설정
                .setMessage("종료하시겠 습니까?")        // 메세지 설정
                .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정
                .setPositiveButton("확인", new DialogInterface.OnClickListener(){
                    // 확인 버튼 클릭시 설정
                    public void onClick(DialogInterface dialog, int whichButton){
                        moveTaskToBack(true);
                        finish();
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener(){
                    // 취소 버튼 클릭시 설정
                    public void onClick(DialogInterface dialog, int whichButton){
                        dialog.cancel();
                    }
                });


        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기

    }
}
