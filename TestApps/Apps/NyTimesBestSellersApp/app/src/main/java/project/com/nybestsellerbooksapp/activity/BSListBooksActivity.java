package project.com.nybestsellerbooksapp.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import project.com.nybestsellerbooksapp.BSImageDBHelper;
import project.com.nybestsellerbooksapp.R;
import project.com.nybestsellerbooksapp.fragment.BSBookItemFragment;
import project.com.nybestsellerbooksapp.model.Item;
import project.com.nybestsellerbooksapp.util.BlurBuilder;

import java.util.ArrayList;
import java.util.List;

public class BSListBooksActivity extends AppCompatActivity implements BSBookItemFragment.OnListFragmentInteractionListener{

    private BSImageDBHelper mHelper;
    final private static String PAGE_TITLE = "Best Sellers";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bslist_books);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(PAGE_TITLE);

        //Getting data from the intent
        Intent i = getIntent();
        String bookListName = i.getStringExtra("listName");
        String bookListEncName = i.getStringExtra("listNameEnc");

        mHelper = new BSImageDBHelper(getApplicationContext());
        String img = mHelper.getImageForCategory(bookListEncName);

        ImageView imgView = (ImageView) findViewById(R.id.backdrop);

        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(img, "drawable", getPackageName()));
        Bitmap blurredBitmap = BlurBuilder.blur( getApplicationContext(), originalBitmap );

        imgView.setImageBitmap( blurredBitmap  );

        TextView categoryTxt = (TextView) findViewById(R.id.category);
        if(bookListName != null){

            //Passing data to fragment

            categoryTxt.setText(bookListName);

            BSBookItemFragment ft =  BSBookItemFragment.newInstance(1, bookListName);
            FragmentManager
                    fragmentManager=
                    getSupportFragmentManager();

            FragmentTransaction fragmentTransaction=
                    fragmentManager.beginTransaction();


            fragmentTransaction.replace(R.id.booksList, ft);
            fragmentTransaction.commit();

        }
         //else throw exception
    }

    public void onListFragmentInteraction(List<Item> item, String bookIdentifier) {

            Intent booksDetailIntent = new Intent(getApplicationContext(), BSBookDetailActivity.class);
            booksDetailIntent.putParcelableArrayListExtra("bookDetailItem", (ArrayList<? extends Parcelable>) item);
            booksDetailIntent.putExtra("bookIdentifier", bookIdentifier);
            startActivity(booksDetailIntent);

    }
}
