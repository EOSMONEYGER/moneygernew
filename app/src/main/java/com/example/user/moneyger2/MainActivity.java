package com.example.user.moneyger2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button infoBtn, calBtn, rankingBtn, searchBtn;

    private FragmentManager fm;

    private final int FRAGMENT_CAL = 0;
    private final int FRAGMENT_INFO = 1;
    private final int FRAGMENT_RANKING = 1;
    private final int FRAGMENT_SEARCH = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // FragmentManager 객체 초기화
        fm = getSupportFragmentManager();

        calBtn = (Button)findViewById(R.id.main_cal_btn);
        infoBtn = (Button)findViewById(R.id.main_info_btn);
        searchBtn = (Button)findViewById(R.id.main_search_btn);
        rankingBtn = (Button)findViewById(R.id.main_ranking_btn);

        calBtn.setOnClickListener(this);
        infoBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);
        rankingBtn.setOnClickListener(this);

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
        } else {
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
            case R.id.tab_intent_btn:
                // intent button 이 눌리면 IntentFragment 로
                replaceFragment(FRAGMENT_INTENT);
                break;
            case R.id.tab_gallery_btn:
                // gallery button 이 눌리면 GalleryFragment 로
                replaceFragment(FRAGMENT_GALLERY);
                break;
        }
    }
}
