package project.com.nybestsellerbooksapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.List;

import project.com.nybestsellerbooksapp.R;
import project.com.nybestsellerbooksapp.fragment.BSBookDetailFragment;
import project.com.nybestsellerbooksapp.model.Item;

public class BSBookDetailActivity extends AppCompatActivity
implements BSBookDetailFragment.OnFragmentInteractionListener{

    final private static String PAGE_TITLE = "Best Seller";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bsbook_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(PAGE_TITLE);

        Intent i= getIntent();
        List<Item> bookDetailItem = i.getParcelableArrayListExtra("bookDetailItem");
        String bookIdentifier = i.getStringExtra("bookIdentifier");

        BSBookDetailFragment ft =  BSBookDetailFragment.newInstance(bookDetailItem, bookIdentifier);
        FragmentManager
                fragmentManager=
                getSupportFragmentManager();

        FragmentTransaction fragmentTransaction=
                fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.bookDetail, ft);
        fragmentTransaction.commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

}
