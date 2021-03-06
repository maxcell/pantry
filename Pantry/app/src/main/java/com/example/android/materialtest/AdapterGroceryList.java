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
public class AdapterGroceryList extends RecyclerView.Adapter<AdapterGroceryList.TextViewHolder> implements ItemTouchHelperAdapter{
    private Context _context;
    private static ArrayList<String> userList;

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        String item = getItem(position);
        remove(item);
        FragmentGroceryList.mAdapter.notifyDataSetChanged();
    }

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
    public AdapterGroceryList(Context context, ArrayList<String> data) {
        this._context = context;
        userList = data;
    }
    // Create new views (invoked by the layout manager)
    @Override
    public AdapterGroceryList.TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


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
            FragmentGroceryList.mAdapter.notifyDataSetChanged();
        }
    }

    public static void remove(String s){
        if(userList.contains(s)){
            userList.remove(s);
            ParseHelper.deleteFromParse(s);
            FragmentGroceryList.mAdapter.notifyDataSetChanged();
        }
    }

    public static String getItem(int position){
        return userList.get(position);
    }
}