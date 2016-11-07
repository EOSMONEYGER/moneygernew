package com.example.user.moneyger2;

import android.app.Activity;
import android.os.Bundle;

import java.lang.reflect.AccessibleObject;
import java.util.Random;

/**
 * Created by User on 2016-11-07.
 */
public class CalResultListActivity extends Activity{
    private int total=0, n=1;
    private String[] ph_num = new String[1000];
    private String[] names = new String[1000];
    private int[] result = new int[n];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal_result_list);

        calculate();

    }
    public void calculate(){
        int[] ch = new int[n];
        int ran;
        int rest=0;
        Random random = new Random();

        for(int i=0;i<n;i++) result[i] = 0;
        if (total % 1000 != 0) {
            ran = random.nextInt(n);

            rest = total%1000;
            ch[ran] = 2;
        }
        total /= 1000;

        for (int i = 0; i < n;i++) result[i] += total / n;
       total %= n;
        while(total > 0){
            ran = random.nextInt(n);
            if (ch[ran] != 0) continue;
            result[ran] ++;
            total--;
        }

        for (int i = 0; i < n; i++) {
            result[i] *= 1000;
            if (ch[i] == 2) result[i] += rest;
        }
    }
}
