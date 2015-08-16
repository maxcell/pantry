package com.example.android.materialtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prince on 7/29/15.
 * Refactored and re-tooled by Justin on 8/4/15.
 */
public class FragmentGroceryList extends Fragment{

    public static AdapterGroceryList mAdapter;
    public View view;
    public ArrayList<String> data;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = inflater.inflate(R.layout.fragment_grocerylist, container, false);

        RecyclerView grocList = (RecyclerView) view.findViewById(R.id.Recycler_GroceryList);
        grocList.setLayoutManager(new LinearLayoutManager(super.getActivity()));
        grocList.setHasFixedSize(true);

        data = ParseHelper.getUserData();


        mAdapter = new AdapterGroceryList(getActivity(), data);

        grocList.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(grocList);

        return view;
    }

    public void onSaveInstanceState(Bundle savedState) {
        super.onSaveInstanceState(savedState);
        savedState.putStringArrayList("groceryListKey", data);

    }




}

