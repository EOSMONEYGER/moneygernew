package com.example.user.moneyger2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by User on 2016-11-06.
 */
public class CalFragment extends Fragment implements View.OnClickListener{

    private Button simpleBtn, originBtn;

    @Nullable

    public static int Total_money=0;
    private EditText total_money;
    @Override


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cal, container, false);

        total_money = (EditText)view.findViewById(R.id.cal_total_money_num);
        simpleBtn = (Button)view.findViewById(R.id.cal_simple_btn);
        originBtn = (Button)view.findViewById(R.id.cal_origin_btn);

        total_money.setText("");

        simpleBtn.setOnClickListener(this);
        originBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view){
        Intent intent;

        if(total_money.getText().toString().length() == 0  || total_money.getText().toString().equals("")){
            Toast toast = Toast.makeText(getContext(), "총액을 입력해주세요", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        Total_money = 0;
        Total_money = Integer.parseInt(total_money.getText().toString());

        switch (view.getId()){
            case R.id.cal_simple_btn:
                intent = new Intent(getActivity(), CalSimpleActivity.class);
                intent.putExtra("total_money", Total_money);
                startActivity(intent);
                break;
            case R.id.cal_origin_btn:
                intent = new Intent(getActivity(), CalOriginActivity.class);
                intent.putExtra("total_money", Total_money);
                startActivity(intent);
                break;
        }
    }
}
