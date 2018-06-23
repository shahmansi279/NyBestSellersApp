package project.com.nybestsellerbooksapp.svc.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import project.com.nybestsellerbooksapp.R;
import project.com.nybestsellerbooksapp.svc.BSFavoritesDBHelper;
import project.com.nybestsellerbooksapp.svc.model.BSBookDetailItem;
import project.com.nybestsellerbooksapp.svc.model.BSBookList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BSBookDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BSBookDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BSBookDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ITEM_TITLE = "title";
    private static final String ARG_ITEM_REVIEW_LINK = "reviewLink";
    private static final String ARG_ITEM_ISBN = "isbn";
    private static final String ARG_ITEM_RANK = "rank";
    private static final String ARG_ITEM_RLW = "rank_last_week";
    private static final String ARG_ITEM_AUTHOR = "author";
    private static final String ARG_ITEM_CONTRIBUTOR = "contributor";
    private static final String ARG_ITEM_DESC = "description";


    private static final String ARG_ITEM_BOOK = "bookItem";

    // TODO: Rename and change types of parameters
    private String mTitle;
    private String mReviewLink;
    private String mDescription;
    private String mAuthor;
    private String mContributor;
    private String mRank;
    private String mRankLastWeek;
    private String mIsbn;


    private BSFavoritesDBHelper mHelper;
    private BSBookList.BSBookItem mBookItem;

    private OnFragmentInteractionListener mListener;

    public BSBookDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BSBookDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BSBookDetailFragment newInstance(BSBookList.BSBookItem
                                                   item) {



        BSBookDetailFragment fragment = new BSBookDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ITEM_TITLE, item.getBookDetails().get(0).getTitle());
        args.putString(ARG_ITEM_REVIEW_LINK, item.getAmazonProductUrl());
        args.putString(ARG_ITEM_ISBN,item.getBookDetails().get(0).getPrimaryIsbn13());
        args.putString(ARG_ITEM_DESC,item.getBookDetails().get(0).getDescription());
        args.putString(ARG_ITEM_AUTHOR,item.getBookDetails().get(0).getAuthor());
        args.putString(ARG_ITEM_CONTRIBUTOR,item.getBookDetails().get(0).getContributor());
        args.putString(ARG_ITEM_RANK, String.valueOf(item.getRank()));
        args.putString(ARG_ITEM_RLW, String.valueOf(item.getRankLastWeek()));

        args.putParcelable(ARG_ITEM_BOOK, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_ITEM_TITLE);
            mReviewLink = getArguments().getString(ARG_ITEM_REVIEW_LINK);
            mIsbn = getArguments().getString(ARG_ITEM_ISBN);
            mAuthor = getArguments().getString(ARG_ITEM_AUTHOR);
            mContributor = getArguments().getString(ARG_ITEM_CONTRIBUTOR);
            mRank = getArguments().getString(ARG_ITEM_RANK);
            mRankLastWeek = getArguments().getString(ARG_ITEM_RLW);
            mBookItem = getArguments().getParcelable(ARG_ITEM_BOOK);
            mDescription = getArguments().getParcelable(ARG_ITEM_DESC);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bsbook_detail, container, false);

        TextView titleView = (TextView)v.findViewById(R.id.title);
        titleView.setText(mTitle);

        TextView prodView = (TextView)v.findViewById(R.id.amazon_prod_url);
        prodView.setText(mReviewLink);

        TextView descView = (TextView)v.findViewById(R.id.description);
        descView.setText(mDescription);

        TextView authorView = (TextView)v.findViewById(R.id.author);
        authorView.setText(mAuthor);

        TextView isbnView = (TextView)v.findViewById(R.id.primary_isbn);
        isbnView.setText(mIsbn);

      //  TextView rankView = (TextView)v.findViewById(R.id.rank);
       // rankView.setText(mRank);

       // TextView rankLastWeekView = (TextView)v.findViewById(R.id.rank_last_week);
       // rankLastWeekView.setText(mRank);

        final ImageButton favBtn = (ImageButton)v.findViewById(R.id.favButton);
        mHelper = new BSFavoritesDBHelper(getContext());


        boolean isFav = mHelper.isFavoriteBook(mIsbn);
        if(mHelper.isFavoriteBook(mIsbn))
            favBtn.setImageDrawable(getResources().getDrawable(R.drawable.btn_star_big_on));
        else
            favBtn.setImageDrawable(getResources().getDrawable(R.drawable.btn_star_big_off));

        favBtn.setOnClickListener( new View.OnClickListener(){

            public void onClick(View v){

                if(mHelper.isFavoriteBook(mIsbn)){

                    mHelper.removeFavoriteBook(mIsbn);
                    favBtn.setImageDrawable(getResources().getDrawable(R.drawable.btn_star_big_off));

                }else    {
                    mHelper.addFavoriteBook(mBookItem);
                    favBtn.setImageDrawable(getResources().getDrawable(R.drawable.btn_star_big_on));
                }

            }
        });

        return v;
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
