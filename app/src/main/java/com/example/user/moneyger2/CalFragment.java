package com.example.user.moneyger2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by User on 2016-11-06.
 */
public class CalFragment extends Fragment implements View.OnClickListener{

    private Button simpleBtn, originBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cal, container, false);

        simpleBtn = (Button)view.findViewById(R.id.cal_simple_btn);
        originBtn = (Button)view.findViewById(R.id.cal_origin_btn);

        simpleBtn.setOnClickListener(this);
        originBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.cal_simple_btn:
                intent = new Intent(getActivity(), CalSimpleActivity.class);
                startActivity(intent);
                break;
            case R.id.cal_origin_btn:
                intent = new Intent(getActivity(), CalOriginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
