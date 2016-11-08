package com.example.user.moneyger2.data;

/**
 * Created by User on 2016-11-08.
 */
public class InfoActData {
    private String name,debt;
    private boolean check_state;//체크여부

    public InfoActData(boolean check_state, String name, String debt){
        this.check_state = check_state;
        this.name = name;
        this.debt = debt;
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


    public String getDebt() {
        return debt;
    }
    public void setDebt(String debt) {
        this.debt = debt;
    }
}
