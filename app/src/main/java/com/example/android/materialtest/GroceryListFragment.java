package com.example.android.materialtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Prince on 7/29/15.
 * Refactored and re-tooled by Justin on 8/4/15.
 */
public class GroceryListFragment extends Fragment {
    public static GroceryListAdapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_grocerylist,container,false);
        RecyclerView grocList = (RecyclerView) v.findViewById(R.id.Recycler_GroceryList);
        grocList.setLayoutManager(new LinearLayoutManager(super.getActivity()));
        grocList.setHasFixedSize(true);

        ArrayList<String> data = new ArrayList<String>();

        mAdapter = new GroceryListAdapter(getActivity(),data);
        grocList.setAdapter(mAdapter);

        return v;
    }
}
