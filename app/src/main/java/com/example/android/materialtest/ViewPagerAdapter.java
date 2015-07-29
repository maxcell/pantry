package com.example.android.materialtest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Prince on 7/29/15.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[];  //Stores the titles of each tab passed to the ViewPagerAdapter
    int nTabs;              // Stores the number of tabs

    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int nNumOfTabs) {
        super(fm);

        this.Titles = mTitles;
        this.nTabs = nNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        if( position == 0 ){
            CardListFragment cardList = new CardListFragment();
            return cardList;
        }
        else {
            Tab2 tab2 = new Tab2();
            return tab2;
        }
    }

    public CharSequence getPageTitle(int position){
        return Titles[position];
    }

    @Override
    public int getCount() {
        return nTabs;
    }
}
