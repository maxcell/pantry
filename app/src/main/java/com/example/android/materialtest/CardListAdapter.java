package com.example.android.materialtest;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Prince on 7/24/15.
 */
public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.CardViewHolder> {

    private Map<String, ArrayList<String>> total = new TreeMap<>();

    // Allows us to pass in the array list
    // Could have used the this keyword but I was lazy
    public CardListAdapter(Map<String, ArrayList<String>> listOfStuff){

        total = listOfStuff;
    }

    @Override
    // Not sure what this does yet.
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);

        CardViewHolder vh = new CardViewHolder(v);

        return vh;
    }

    @Override
    // Adds each individual item into a view
    // This allows each card to have different text
    public void onBindViewHolder(CardViewHolder cardViewHolder, int i) {
            cardViewHolder.mCategory.setText((String) (total.keySet().toArray())[i]);



                cardViewHolder.mItems.setText(total.get((total.keySet().toArray())[i]).get(0));

//              cardViewHolder.mItem1.setText(total.get((total.keySet().toArray())[0]).get(0));
//              cardViewHolder.mItem2.setText(total.get((total.keySet().toArray())[0]).get(1));
//              cardViewHolder.mItem3.setText(total.get((total.keySet().toArray())[0]).get(2));


    }

    @Override
    // Returns the size of our entire list
    public int getItemCount() {
        return total.keySet().size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder{
        public TextView mCategory;

        public TextView mItems;
//        public TextView mItem1;
//        public TextView mItem2;
//        public TextView mItem3;

        public CardViewHolder(LinearLayout itemView){
            super(itemView);
            mCategory = (TextView) itemView.findViewById(R.id.text);
            mItems = (TextView) itemView.findViewById(R.id.item_view);


//            mItem1 = (TextView) itemView.findViewById(R.id.item_view1);
//            mItem2 = (TextView) itemView.findViewById(R.id.item_view2);
//            mItem3 = (TextView) itemView.findViewById(R.id.item_view3);
        }

    }
}
