package com.example.user.moneyger2;

import android.content.Intent;
import android.net.Uri;
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

import com.example.user.moneyger2.adapter.SearchAdapter;
import com.example.user.moneyger2.data.SearchData;
import com.example.user.moneyger2.dbsql.DBManager;

import java.util.ArrayList;

/**
 * Created by User on 2016-11-06.
 */
public class SearchFragment extends Fragment{
    private Button select_btn, send_btn;

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
        select_btn = (Button)view.findViewById(R.id.search_select_btn);
        send_btn = (Button)view.findViewById(R.id.search_send_btn);


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

        select_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean ch = false;
                if(searchList.size() != 0) {
                    for (int i = 0; i < searchList.size(); i++) {
                        if (searchList.get(i).isCheck_state() == false) ch = true;
                        searchList.get(i).setCheck_state(true);
                    }
                    if (ch == false) {
                        for (int i = 0; i < searchList.size(); i++)
                            searchList.get(i).setCheck_state(false);
                    }
                    searchView.setAdapter(new SearchAdapter(getActivity(), searchList));
                } else{
                    for (int i = 0; i < search_fragList.size(); i++) {
                        if (search_fragList.get(i).isCheck_state() == false) ch = true;
                        search_fragList.get(i).setCheck_state(true);
                    }
                    if (ch == false) {
                        for (int i = 0; i < search_fragList.size(); i++)
                            search_fragList.get(i).setCheck_state(false);
                    }
                    searchView.setAdapter(new SearchAdapter(getActivity(), search_fragList));
                }
            }
        });

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ph_num, debt;
                for(int i=0;i<search_fragList.size();i++){
                    ph_num = search_fragList.get(i).getPh_num();
                    debt = search_fragList.get(i).getDebt();
                }
                Uri uri = Uri.parse("smsto:01022865413");
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra("sms_body","[MONEYGER]\n"+"2016.11.13\n"+"EOS 정기 회합"+"\n작은딸애게 신한 110438358091"+"\n으로 입금해주세요.^^");
                startActivity(intent);
            }
        });

        return view;
    }
    public ArrayList<SearchData> getsearch_fragList(){
        search_fragList = new DBManager(getContext()).getSearch();

        return search_fragList;
    }
}
