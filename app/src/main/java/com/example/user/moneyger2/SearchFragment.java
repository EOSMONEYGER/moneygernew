package com.example.user.moneyger2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.moneyger2.adapter.CalOriginAdapter;
import com.example.user.moneyger2.adapter.SearchAdapter;
import com.example.user.moneyger2.data.CalOriginData;
import com.example.user.moneyger2.data.InfoActData;
import com.example.user.moneyger2.data.SearchData;
import com.example.user.moneyger2.DBsql.DBManager;
import com.example.user.moneyger2.DBsql.MySQLOpenHelper;

import java.util.ArrayList;

/**
 * Created by User on 2016-11-06.
 */
public class SearchFragment extends Fragment{
    SQLiteDatabase db;
    MySQLOpenHelper helper;
    private final static String TABLE_NAME = "debtlist";

    private RecyclerView searchView;
    private EditText search_edit;
    private ArrayList<SearchData> search_fragList;
    private ArrayList<SearchData> searchList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchView = (RecyclerView)view.findViewById(R.id.search_list_view);
        search_edit = (EditText)view.findViewById(R.id.search_edit);
        // RecyclerView 에 LinearLayoutManager를 셋팅
        searchView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // 만들어 둔 IntentAdapter를 RecyclerView에 셋팅
        searchView.setAdapter(new SearchAdapter(getActivity(), getsearch_fragList()));

        search_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = editable.toString();

                searchList.clear();

                if(s.length() == 0) {
                    searchView.setAdapter(new SearchAdapter(getActivity(), search_fragList));
                } else {
                    for (int j = 0; j < search_fragList.size(); j++) {
                        if (search_fragList.get(j).getName().contains(s)) {
                            searchList.add(search_fragList.get(j));
                        }
                    }
                    searchView.setAdapter(new SearchAdapter(getActivity(), searchList));
                }

            }
        });

        return view;
    }
    public ArrayList<SearchData> getsearch_fragList(){
        search_fragList = new DBManager(getContext()).getSearch();

        return search_fragList;
    }
}
