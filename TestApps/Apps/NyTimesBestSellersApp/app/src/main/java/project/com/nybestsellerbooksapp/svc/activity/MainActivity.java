package project.com.nybestsellerbooksapp.svc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import project.com.nybestsellerbooksapp.R;
import project.com.nybestsellerbooksapp.svc.adapter.BSFragmentPagerAdapter;
import project.com.nybestsellerbooksapp.svc.fragment.BSFavoriteItemFragment;
import project.com.nybestsellerbooksapp.svc.fragment.BSHistoryItemFragment;
import project.com.nybestsellerbooksapp.svc.fragment.BSListItemFragment;
import project.com.nybestsellerbooksapp.svc.model.BSFavoriteBookItem;
import project.com.nybestsellerbooksapp.svc.model.BSHistoryList;
import project.com.nybestsellerbooksapp.svc.model.BSList;

public class MainActivity extends AppCompatActivity implements  BSListItemFragment.OnListFragmentInteractionListener , BSFavoriteItemFragment.OnListFragmentInteractionListener, BSHistoryItemFragment.OnListFragmentInteractionListener {

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

        String[] titles = {"Best Seller","Favorites","History"};

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            setActionBarTitle(titles[position]);


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
        startActivity(booksIntent);
    }

    @Override
    public void onListFragmentInteraction(BSFavoriteBookItem item){

    }

    @Override
    public void onListFragmentInteraction(BSHistoryList.BSHistoryBookItem item){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

       inflater.inflate(R.menu.options_menu, menu);
       return true;
    }

}
