package com.example.user.moneyger2.data;

/**
 * Created by User on 2016-11-08.
 */
public class CalResData {
    private String name, debt;

    public CalResData(String name,String debt){
        this.name = name;
        this.debt = debt;
    }

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String getDebt(){return debt;}
    public void setDebt(String debt){this.debt = debt;}
}
