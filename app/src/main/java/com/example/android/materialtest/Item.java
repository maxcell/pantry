package com.example.android.materialtest;

/**
 * Created by Prince on 7/28/15.
 */

// A Future class that might need to be taken advantage of to put items into
// Definitely will come back to this
public class Item {

    private String category;
    private String name;
    private String info;

    public Item(String mCategory, String mName, String mInfo){
        this.category = mCategory;
        this.name = mName;
        this.info = mInfo;
    }

    public String getName(){
        return name;
    }

    public String getCategory(){
        return category;
    }

    public String getInfo(){
        return info;
    }
}
