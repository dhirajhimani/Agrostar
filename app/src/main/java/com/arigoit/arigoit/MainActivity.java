package com.arigoit.arigoit;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.arigoit.arigoit.Utils.Constants;
import com.arigoit.arigoit.database.DatabaseHandler;
import com.arigoit.arigoit.fragment.ImageSliderFragment;
import com.arigoit.arigoit.fragment.MainActivityFragment;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private MenuItem mSearchMenu;
    private SearchView searchView;
    private boolean doubleBackToExitPressedOnce = false;
    private FloatingActionButton fab_uk;
    private FloatingActionButton fab_spanish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLocale();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab_uk = (FloatingActionButton) findViewById(R.id.fab_uk);
        fab_spanish = (FloatingActionButton) findViewById(R.id.fab_spanish);
        initFragments();
        insertDummyIfDbEmpty();
        setCurrentLocale();
    }

    private void setCurrentLocale() {
        if (Constants.isEnglishLocale(this)) {
            fab_uk.setVisibility(View.VISIBLE);
            fab_spanish.setVisibility(View.GONE);
        } else {
            fab_uk.setVisibility(View.GONE);
            fab_spanish.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Check the DB count by number of items
     */
    private void insertDummyIfDbEmpty() {
        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        if (databaseHandler.getProductsCount() == 0) {
            insertDummyItems(databaseHandler);
        }
    }

    private void insertDummyItems(DatabaseHandler databaseHandler) {
        databaseHandler.addProducts(Constants.getDummyProducts());
    }

    private void initFragments() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.gridfragment, new MainActivityFragment());
        fragmentTransaction.commit();
        //Image Slider Fragment
        fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.sliderfragment, new ImageSliderFragment());
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        // Inflate menu to add items to action bar if it is present.
        inflater.inflate(R.menu.menu_main, menu);
        mSearchMenu = menu.findItem(R.id.menu_search);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView =
                (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        // Get the search close button image view
        ImageView closeButton = (ImageView)searchView.findViewById(R.id.search_close_btn);

        // Set on click listener
        closeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Find EditText view
                EditText et = (EditText) findViewById(R.id.search_src_text);

                //Clear the text from EditText view
                et.setText("");

                //Clear query
                searchView.setQuery("", false);
                //Collapse the action view
                searchView.onActionViewCollapsed();
                //Collapse the search widget
                mSearchMenu.collapseActionView();
            }
        });


//        searchView.setIconified(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_locale) {

            Constants.toggleLocale(this);
            recreate();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (searchView != null) {
            searchView.onActionViewCollapsed();
        }
        if (mSearchMenu != null) {
            mSearchMenu.collapseActionView();
        }
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getResources().getString(R.string.back_exit), Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    public void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setLocale();
    }

    private void setLocale() {

        final Resources resources = getResources();
        final Configuration configuration = resources.getConfiguration();
        final Locale locale = Constants.getLocale(this);
        if (!configuration.locale.equals(locale)) {
            configuration.setLocale(locale);
            resources.updateConfiguration(configuration, null);
        }
    }

}
