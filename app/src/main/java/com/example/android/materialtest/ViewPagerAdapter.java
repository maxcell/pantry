package com.example.android.materialtest;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

/**
 * Created by Prince on 7/29/15.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private CharSequence Titles[];  //Stores the titles of each tab passed to the ViewPagerAdapter
    private int nTabs;              // Stores the number of tabs
    private Context context;


    // Trying to add icons instead of titles
    private int[] imageResId ={
            R.drawable.ic_ingr_list_dark,
            R.drawable.ic_shopping_cart_dark,
    };

    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int nNumOfTabs, Context context) {
        super(fm);

        this.Titles = mTitles;
        this.nTabs = nNumOfTabs;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        if( position == 0 ){
            CardListFragment cardList = new CardListFragment();
            return cardList;
        }
        else {
            GroceryListFragment groceryList = new GroceryListFragment();
            return groceryList;
        }
    }



    public CharSequence getPageTitle(int position){
        Drawable image = this.context.getResources().getDrawable(imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return sb;
    }

    @Override
    public int getCount() {
        return nTabs;
    }

    @Override
    public float getPageWidth(int position){
        float nbPages;
        if(isTablet(context) && (context.getResources().getConfiguration().orientation == 2)){
            nbPages = 2;
        } else {
            nbPages = 1;
        }

        return (1 / nbPages);
    }

    public static boolean isTablet(Context context){
        return (context.getResources().getConfiguration().screenLayout
                    & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }
}
