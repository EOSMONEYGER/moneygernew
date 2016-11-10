package com.example.user.moneyger2.data;

/**
 * Created by User on 2016-11-08.
 */
public class InfoActData {
    private String name,debt,ph_num;
    private boolean check_state;//체크여부
    private int year,month,day;

    public InfoActData(boolean check_state, String name, String debt){
        this.check_state = check_state;
        this.name = name;
        this.debt = debt;
    }
    public InfoActData(boolean check_state, String name, String debt, String ph_num, int year, int month, int day){
        this.check_state = check_state;
        this.name = name;
        this.debt = debt;
        this.ph_num = ph_num;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public void setPh_num(String ph_num) {this.ph_num = ph_num;}
    public String getPh_num() {return ph_num;}

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

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }
    public int getDay(){
        return day;
    }
}
