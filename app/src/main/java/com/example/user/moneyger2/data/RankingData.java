package com.example.user.moneyger2.data;

/**
 * Created by User on 2016-11-08.
 */
public class RankingData {
    private String rank,name,debt;
    public RankingData(String rank, String name, String debt){
        this.rank = rank;
        this.name = name;
        this.debt = debt;
    }

    public String getRank(){return rank;}
    public void setRank(String rank){this.rank = rank;}

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String getDebt(){return debt;}
    public void setDebt(String debt){this.debt = debt;}
}
