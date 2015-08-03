package com.example.android.materialtest;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Prince on 8/3/15.
 */
public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.TextViewHolder> {
    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(TextViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder{
        // The category
        public TextView mCategory;

        // The first three items
        public TextView mItem1;
        public TextView mItem2;
        public TextView mItem3;

        public TextViewHolder(LinearLayout itemView){
            super(itemView);
            mCategory = (TextView) itemView.findViewById(R.id.text);


            mItem1 = (TextView) itemView.findViewById(R.id.item_view1);
            mItem2 = (TextView) itemView.findViewById(R.id.item_view2);
            mItem3 = (TextView) itemView.findViewById(R.id.item_view3);
        }

    }
}
