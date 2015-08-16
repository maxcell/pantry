package com.example.android.materialtest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Prince on 7/24/15.
 */
public class AdapterCardList extends RecyclerView.Adapter<AdapterCardList.CardViewHolder> {

    private Map<String, ArrayList<String>> total = new TreeMap<>();
    private Context _context;
    private ArrayList<String> userList = ParseHelper.getUserData();

    // Allows us to pass in the array list
    // Could have used the this keyword but I was lazy
    public AdapterCardList(Context context, GroceryStore groceryStore){

        total = groceryStore.getCategories();
        this._context = context;
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
    public void onBindViewHolder(final CardViewHolder cardViewHolder, int i) {
        cardViewHolder.mCategory.setText((String) (total.keySet().toArray())[i]);

        // Setting the text for item 1 and the clickable action
        cardViewHolder.mItem1.setText(total.get((total.keySet().toArray())[i]).get(0));
        cardViewHolder.mItem1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                AdapterGroceryList.addToList((String) cardViewHolder.mItem1.getText());
                if (!userList.contains(cardViewHolder.mItem1.getText())) {

                    ParsePantry obj = new ParsePantry();
                    obj.setTitle(cardViewHolder.mItem1.getText().toString());
                    obj.setAuthor(ParseUser.getCurrentUser());
                    obj.pinInBackground();
                }

                CharSequence text = cardViewHolder.mItem1.getText() + " added to grocery list. Yum!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(_context, text, duration);
                toast.show();
            }
        });

        // Setting the text for item 2 and the clickable action
        cardViewHolder.mItem2.setText(total.get((total.keySet().toArray())[i]).get(1));
        cardViewHolder.mItem2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                AdapterGroceryList.addToList((String) cardViewHolder.mItem2.getText());
                if(!userList.contains(cardViewHolder.mItem2.getText())) {
                    ParsePantry obj = new ParsePantry();
                    obj.setTitle(cardViewHolder.mItem2.getText().toString());
                    obj.setAuthor(ParseUser.getCurrentUser());
                    obj.pinInBackground();
                }
                CharSequence text = cardViewHolder.mItem2.getText() + " added to grocery list. Yum!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(_context, text, duration);
                toast.show();
            }
        });

        // Setting the text for item 3 and the clickable action
        cardViewHolder.mItem3.setText(total.get((total.keySet().toArray())[i]).get(2));
        cardViewHolder.mItem3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                AdapterGroceryList.addToList((String) cardViewHolder.mItem3.getText());
                if(!userList.contains(cardViewHolder.mItem3.getText())) {

                    ParsePantry obj = new ParsePantry();
                    obj.setTitle(cardViewHolder.mItem3.getText().toString());
                    obj.setAuthor(ParseUser.getCurrentUser());
                    obj.pinInBackground();
                }
                CharSequence text = cardViewHolder.mItem3.getText() + " added to grocery list. Yum!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(_context, text, duration);
                toast.show();
            }
        });

        TextView moreItems = (TextView) cardViewHolder.itemView.findViewById(R.id.storeMore);


        moreItems.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(_context, ActivityTotalList.class);
                        intent.putExtra("category", (String) cardViewHolder.mCategory.getText());
                        _context.startActivity(intent);
                    }
                });
    }

    @Override
    // Returns the size of our entire list
    public int getItemCount() {
        return total.keySet().size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder{
        // The category
        public TextView mCategory;

        // The first three items
        public TextView mItem1;
        public TextView mItem2;
        public TextView mItem3;

        public CardViewHolder(LinearLayout itemView){
            super(itemView);
            mCategory = (TextView) itemView.findViewById(R.id.text);


            mItem1 = (TextView) itemView.findViewById(R.id.item_view1);
            mItem2 = (TextView) itemView.findViewById(R.id.item_view2);
            mItem3 = (TextView) itemView.findViewById(R.id.item_view3);
        }

    }
}