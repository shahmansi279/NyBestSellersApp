package project.com.nybestsellerbooksapp.svc.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import project.com.nybestsellerbooksapp.R;
import project.com.nybestsellerbooksapp.svc.fragment.BSBookDetailFragment;
import project.com.nybestsellerbooksapp.svc.model.BSBookList;

public class BSBookDetailActivity extends AppCompatActivity
implements BSBookDetailFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bsbook_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent i= getIntent();
        BSBookList.BSBookItem bookItem = (BSBookList.BSBookItem) i.getParcelableExtra("bookItem");

        BSBookDetailFragment ft =  BSBookDetailFragment.newInstance(bookItem);
        FragmentManager
                fragmentManager=
                getSupportFragmentManager();

        FragmentTransaction fragmentTransaction=
                fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.bookDetail, ft);
        fragmentTransaction.commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
