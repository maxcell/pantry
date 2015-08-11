package com.example.android.materialtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Justin on 8/4/15.
 */
public class GroceryListAdapter extends RecyclerView.Adapter<GroceryListAdapter.TextViewHolder> {
    private Context _context;
    private static ArrayList<String> userList;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class TextViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;

        public TextViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public GroceryListAdapter(Context context, ArrayList<String> data) {
        this._context = context;
        userList = data;
    }
    // Create new views (invoked by the layout manager)
    @Override
    public GroceryListAdapter.TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


    TextView v = (TextView) LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_view, parent, false);

    TextViewHolder vh = new TextViewHolder(v);

    return vh;

}
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(TextViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(userList.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static void addToList(String s){
        if(! userList.contains(s)) {
            userList.add(s);
            GroceryListFragment.mAdapter.notifyDataSetChanged();
        }
    }
}