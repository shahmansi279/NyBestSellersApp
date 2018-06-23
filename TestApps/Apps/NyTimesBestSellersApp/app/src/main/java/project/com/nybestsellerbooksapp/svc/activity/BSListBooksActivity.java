package project.com.nybestsellerbooksapp.svc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import project.com.nybestsellerbooksapp.R;
import project.com.nybestsellerbooksapp.svc.fragment.BSBookItemFragment;
import project.com.nybestsellerbooksapp.svc.fragment.BSListItemFragment;
import project.com.nybestsellerbooksapp.svc.model.BSBookList;
import project.com.nybestsellerbooksapp.svc.model.BSList;

public class BSListBooksActivity extends AppCompatActivity implements BSBookItemFragment.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_bslist_books);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
;
        //Getting data from the intent
        Intent i = getIntent();
        String bookListName = i.getStringExtra("listName");

        if(bookListName != null){

            //Passing data to fragment

            BSBookItemFragment ft =  BSBookItemFragment.newInstance(1, bookListName);
            FragmentManager
                    fragmentManager=
                    getSupportFragmentManager();

            FragmentTransaction fragmentTransaction=
                    fragmentManager.beginTransaction();

            fragmentTransaction.add(R.id.booksList, ft);
            fragmentTransaction.commit();

        }
         //else throw exception

    }

    public void onListFragmentInteraction(BSBookList.BSBookItem item) {

        //Invoke BSBooks Activity
        Intent bsBooks = new Intent(getApplicationContext(),BSBookDetailActivity.class);
        bsBooks.putExtra("bookItem", (Parcelable) item);
        startActivity(bsBooks);
    }
}
