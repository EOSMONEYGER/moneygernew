package com.example.user.moneyger2.data;

/**
 * Created by User on 2016-11-08.
 */
public class CalOriginData {
    private String name,ph_num;
    private boolean check_state;//체크여부

    public CalOriginData(boolean check_state, String name,String ph_num){
        this.check_state = check_state;
        this.name = name;
        this.ph_num = ph_num;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheck_state() {
        return check_state;
    }
    public void setCheck_state(boolean check_state) {
        this.check_state = check_state;
    }


    public String getPh_num() {
        return ph_num;
    }
    public void setPh_num(String ph_num) {
        this.ph_num = ph_num;
    }
}
