package com.example.user.moneyger2.data;

/**
 * Created by User on 2016-11-07.
 */
public class InfoData {
    private String date, gathering;
    private int imageId;

    public InfoData(String date, String gathering, int imageId){
        this.date = date;
        this.gathering = gathering;
        this.imageId = imageId;
    }

    public String getDate(){return date;}
    public void setDate(String date){this.date = date;}

    public String getGathering(){return gathering;}
    public void setGathering(String gathering){this.gathering = gathering;}

    public int getImageId(){return imageId;}
    public void setImageId(int imageId){this.imageId = imageId;}
}
