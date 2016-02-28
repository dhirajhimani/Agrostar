package com.arigoit.arigoit;

/**
 * Created by dhiraj on 27-02-2016.
 */

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.arigoit.arigoit.fragment.ImageSliderFragment;
import com.arigoit.arigoit.fragment.MainActivityFragment;

public class SearchResultsActivity extends ActionBarActivity {

    TextView search_src_text;
    private SearchView searchView;
    private String searchString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        search_src_text = (TextView)findViewById(R.id.search_src_text);
        handleIntent(getIntent());
    }

    private void initFragments(String searchString) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = fragmentManager.beginTransaction();
        MainActivityFragment mainActivityFragment = new MainActivityFragment();
        if (!TextUtils.isEmpty(searchString)) {
            Bundle arguments = new Bundle();
            arguments.putString(MainActivityFragment.SEARCH_TAG, searchString);
            mainActivityFragment.setArguments(arguments);
            this.searchString = searchString;
            updateSearchView();
        }
        fragmentTransaction.replace(R.id.gridfragment, mainActivityFragment);
        fragmentTransaction.commit();
    }

    private void updateSearchView() {
        if (searchView != null && !TextUtils.isEmpty(searchString)) {
            EditText searchText = (EditText)searchView.findViewById(R.id.search_src_text);
            if (searchText != null) {
                searchView.onActionViewExpanded();
                searchText.setText(searchString + "");
            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView =
                (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        updateSearchView();
        menu.findItem(R.id.action_locale).setVisible(false);

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search
            Log.d("ACTION_SEARCH", ""+query);
            initFragments(query);
        }
    }
}

