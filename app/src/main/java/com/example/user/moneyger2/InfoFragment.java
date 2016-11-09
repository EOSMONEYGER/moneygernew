package com.example.user.moneyger2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.moneyger2.DBsql.MySQLOpenHelper;
import com.example.user.moneyger2.adapter.InfoAdapter;
import com.example.user.moneyger2.data.InfoData;

import java.util.ArrayList;

/**
 * Created by User on 2016-11-06.
 */
public class InfoFragment extends Fragment {
    SQLiteDatabase db;
    MySQLOpenHelper helper;
    private final static String TABLE_NAME = "debtlist";

    private RecyclerView infoView;
    private ArrayList<InfoData> infoList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        infoView = (RecyclerView)view.findViewById(R.id.info_view);

        // RecyclerView 에 LinearLayoutManager를 셋팅
        infoView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // 만들어 둔 IntentAdapter를 RecyclerView에 셋팅
        infoView.setAdapter(new InfoAdapter(getActivity(), getInfoList()));

        return view;
    }

    public ArrayList<InfoData> getInfoList() {

        helper = new MySQLOpenHelper(getContext());//헬퍼를 사용하여
        try{
            db = helper.getWritableDatabase();//DB를 쓰기가능으로 연다.
        } catch(SQLiteException e){
            db = helper.getReadableDatabase();//에러 시 읽기전용으로.
        }

        Cursor csr = db.query(TABLE_NAME, null, null, null, null, null, null);//선택 조건 없는 쿼리 실행(=DB 테이블의 레코드 전체를가져옴)

        String ch = "";
        while(csr.moveToNext()){//커서를 처음레코드부터 마지막레코드까지 이동하며 반복.//
            if(!ch.equals(csr.getString(4))) {
                infoList.add(new InfoData(csr.getInt(5) + "." + csr.getInt(6) + "." + csr.getInt(7), csr.getString(4), R.drawable.icon_next_mini));
                ch = csr.getString(4);
            }
        }

        return infoList;
    }
}
