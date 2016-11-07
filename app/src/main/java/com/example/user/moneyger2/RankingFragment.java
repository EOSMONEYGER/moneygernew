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
import com.example.user.moneyger2.adapter.RankingAdapter;
import com.example.user.moneyger2.data.InfoData;
import com.example.user.moneyger2.data.RankingData;

import java.util.ArrayList;

/**
 * Created by User on 2016-11-06.
 */
public class RankingFragment extends Fragment {

    private RecyclerView rankingView;
    private ArrayList<RankingData> rankingList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);

        rankingView = (RecyclerView)view.findViewById(R.id.ranking_view);

        // RecyclerView 에 LinearLayoutManager를 셋팅
        rankingView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // 만들어 둔 IntentAdapter를 RecyclerView에 셋팅
        rankingView.setAdapter(new RankingAdapter(getActivity(), getRankingList()));

        return view;
    }
    public ArrayList<RankingData> getRankingList() {
        if (rankingList.size() == 0) {
            rankingList.add(new RankingData("1st","김나용","100won"));
        }

        return rankingList;
    }
}
