package com.example.android.materialtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by Prince on 8/3/15.
 */
public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.TextViewHolder> {
    private Context _context;
    private String _category;
    private TreeMap<String, ArrayList<String>> items;

    public ItemListAdapter(Context context, GroceryStore groceryStore, String category){
        this._context = context;
        this.items = groceryStore.getCategories();
        this._category = category;
    }

    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);

        TextViewHolder th = new TextViewHolder(v);

        return th;
    }

    @Override
    public void onBindViewHolder(final TextViewHolder holder, int position) {
        String data = (GroceryStore.getInstance().getCategories().get(_category).get(position));
        holder.mItem.setText(data);
        holder.mItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                GroceryListAdapter.addToList((String) holder.mItem.getText());
                CharSequence text = holder.mItem.getText() + " added to grocery list. Yum!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(_context, text, duration);
                toast.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return GroceryStore.getInstance().getCategories().get(_category).size();
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder{

        // The item in a category
        public TextView mItem;

        public TextViewHolder(View itemView){
            super(itemView);
            mItem = (TextView) itemView.findViewById(R.id.item_view);

        }

    }
}
