package com.example.user.moneyger2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.moneyger2.adapter.InfoAdapter;
import com.example.user.moneyger2.data.InfoData;

import java.util.ArrayList;

/**
 * Created by User on 2016-11-06.
 */
public class InfoFragment extends Fragment {
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
        if (infoList.size() == 0) {

            infoList.add(new InfoData("2016.04.21", "에오스 회합", R.drawable.icon_next_mini));
            infoList.add(new InfoData("1220.22.22", "오아아아ㅏㅇfrrrrrrrrr아ㅏㅏㅏㅇ", R.drawable.icon_next_mini));
            infoList.add(new InfoData("1220.23.22", "rjqegqgo2", R.drawable.icon_next_mini));
        }

        return infoList;
    }
}
