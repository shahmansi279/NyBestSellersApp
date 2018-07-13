package com.bestreads.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import com.bestreads.R;
import com.bestreads.adapter.BSFragmentPagerAdapter;
import com.bestreads.fragment.BSFavoriteItemFragment;
import com.bestreads.fragment.BSHistoryItemFragment;
import com.bestreads.fragment.BSListItemFragment;
import com.bestreads.model.BSList;
import com.bestreads.model.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  BSListItemFragment.OnListFragmentInteractionListener , BSFavoriteItemFragment.OnListFragmentInteractionListener, BSHistoryItemFragment.OnHistoryListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        BSFragmentPagerAdapter adapter = new BSFragmentPagerAdapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        String[] titles = {"New York Times Best Selling Lists","Favorites","Best Sellers History"};

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            setActionBarTitle(titles[position]);
            Fragment fragment = ((BSFragmentPagerAdapter) viewPager.getAdapter()).getFragment(position);

            if(fragment != null){
                fragment.onResume();
                adapter.notifyDataSetChanged();
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    });

    }
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }


    @Override
    public void onListFragmentInteraction(BSList.BSListItem item) {

        //Invoke BSBooks Activity
        Intent booksIntent = new Intent(getApplicationContext(),BSListBooksActivity.class);
        booksIntent.putExtra("listName",item.getListName());
        booksIntent.putExtra("listNameEnc", item.getListNameEncoded());
        startActivity(booksIntent);
    }

    @Override
    public void onFavoriteListFragmentInteraction(List<Item> item, String bookIdentifier){


        Intent booksDetailIntent = new Intent(getApplicationContext(), BSBookDetailActivity.class);
        booksDetailIntent.putParcelableArrayListExtra("bookDetailItem", (ArrayList<? extends Parcelable>) item);
        booksDetailIntent.putExtra("bookIdentifier",bookIdentifier);
        startActivity(booksDetailIntent);

    }

    @Override
    public void onHistoryListFragmentInteraction(List<Item> item, String bookIdentifier){

        Intent booksDetailIntent = new Intent(getApplicationContext(), BSBookDetailActivity.class);
        booksDetailIntent.putParcelableArrayListExtra("bookDetailItem", (ArrayList<? extends Parcelable>) item);
        booksDetailIntent.putExtra("bookIdentifier", bookIdentifier);
        startActivity(booksDetailIntent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

       inflater.inflate(R.menu.options_menu, menu);
       return true;
    }

}
