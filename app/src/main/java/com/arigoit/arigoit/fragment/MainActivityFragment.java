package com.arigoit.arigoit.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.arigoit.arigoit.R;
import com.arigoit.arigoit.adapter.ProductAdapter;
import com.arigoit.arigoit.database.DatabaseHandler;
import com.arigoit.arigoit.model.ProductInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public static String SEARCH_TAG = "SEARCH_TAG";

    public MainActivityFragment() {
    }
    DatabaseHandler databaseHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        GridView gridview = (GridView)view.findViewById(R.id.gridview);
        databaseHandler = new DatabaseHandler(getActivity());

        //
        String searchString = getArguments() != null ? getArguments().getString(SEARCH_TAG) : null;
        List<ProductInfo> allItems = (searchString == null
                                                        ? databaseHandler.getAllProducts()
                                                        : databaseHandler.searchProduct(searchString));
        ProductAdapter customAdapter = new ProductAdapter(getActivity(), allItems);
        gridview.setAdapter(customAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Position: " + position, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


}
