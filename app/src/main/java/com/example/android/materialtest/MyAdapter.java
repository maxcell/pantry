package com.example.android.materialtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Prince on 7/24/15.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CardViewHolder> {

    private int nCards;

    public MyAdapter(int numCards){
        nCards = numCards;
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
        cardViewHolder.setText(i);
    }

    @Override
    public int getItemCount() {
        return nCards;
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder{
        public TextView mCardNum;

        public CardViewHolder(LinearLayout itemView){
            super(itemView);
            mCardNum = (TextView) itemView.findViewById(R.id.text);
        }

        public void setText(int num) { mCardNum.setText("This is card " + num); }
    }
}
