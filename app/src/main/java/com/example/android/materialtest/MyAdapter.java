package com.example.android.materialtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Prince on 7/24/15.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CardViewHolder> {

    private ArrayList<String> categories = new ArrayList<>();
    private int nCards;

    public MyAdapter( ArrayList<String> listOfCategories){

        categories = listOfCategories;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);

        CardViewHolder vh = new CardViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(CardViewHolder cardViewHolder, int i) {
            cardViewHolder.mCategory.setText(categories.get(i));

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder{
        public TextView mCategory;

        public CardViewHolder(LinearLayout itemView){
            super(itemView);
            mCategory = (TextView) itemView.findViewById(R.id.text);
        }

    }
}
