package project.com.nybestsellerbooksapp.svc.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import project.com.nybestsellerbooksapp.R;
import project.com.nybestsellerbooksapp.svc.BSFavoritesDBHelper;
import project.com.nybestsellerbooksapp.svc.model.SaleInfo;
import project.com.nybestsellerbooksapp.svc.model.VolumeInfo;

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
    private static final String ARG_ITEM_AUTHOR = "author";
    private static final String ARG_ITEM_ISBN = "isbn";
    private static final String ARG_ITEM_DESC = "description";
    private static final String ARG_ITEM_PUBLISHER = "publisher";
    private static final String ARG_ITEM_PRICE = "price";
    private static final String ARG_ITEM_PAGES = "pages";
    private static final String ARG_ITEMS_PREVIEW_LINK = "previewLink";
    private static final String ARG_ITEMS_PUBLISHED_DATE = "publishedDate";
    private static final String ARG_ITEMS_THUMBNAIL_IMAGE = "imageUrl";


    // TODO: Rename and change types of parameters
    private String mTitle;
    private String mPreviewLink;
    private String mPublisher;
    private String mPublishedDate;
    private String mThumbnail;
    private String mDescription;
    private String mAuthor;
    private String mPrice;
    private Integer mPages;
    private String mIsbn;
    private boolean isDetailInfoAvailable = false;

    private BSFavoritesDBHelper mHelper;

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
    public static BSBookDetailFragment newInstance(VolumeInfo
                                                   volumeInfo, SaleInfo saleInfo) {



        BSBookDetailFragment fragment = new BSBookDetailFragment();
        Bundle args = new Bundle();

        if(volumeInfo != null && saleInfo != null){

          VolumeInfo bookVolume = volumeInfo;
          SaleInfo bookSales = saleInfo;
          args.putString(ARG_ITEM_ISBN,bookVolume.getIndustryIdentifiers().get(0).getIdentifier());
          args.putString(ARG_ITEM_TITLE,bookVolume.getTitle());
          //  args.putString(ARG_ITEM_REVIEW_LINK, item.);
            args.putString(ARG_ITEM_DESC,bookVolume.getDescription());

            //combine authors

            StringBuilder authors =  new StringBuilder();
            for(String author: bookVolume.getAuthors()){
                authors.append(author);
                authors.append(" & ");

            }
            authors.lastIndexOf("&");
            int index = authors.lastIndexOf("&");
            authors.replace(index, index, "");
            args.putString(ARG_ITEM_AUTHOR,new String(authors).trim());
            args.putString(ARG_ITEM_PUBLISHER,bookVolume.getPublisher());

            if(bookSales.getRetailPrice() != null)
                 args.putString(ARG_ITEM_PRICE, bookSales.getRetailPrice().getAmount() + " " + bookSales.getRetailPrice().getCurrencyCode() );
            else
                args.putString(ARG_ITEM_PRICE, "NOT FOR SALE");

            args.putInt(ARG_ITEM_PAGES, bookVolume.getPageCount());
            args.putString(ARG_ITEMS_PREVIEW_LINK, bookVolume.getPreviewLink());
            args.putString(ARG_ITEMS_PUBLISHED_DATE, bookVolume.getPublishedDate());
            args.putString(ARG_ITEMS_THUMBNAIL_IMAGE, bookVolume.getImageLinks().getThumbnail());
            fragment.setArguments(args);
        }


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            isDetailInfoAvailable = true;
            mTitle = getArguments().getString(ARG_ITEM_TITLE);
            mAuthor = getArguments().getString(ARG_ITEM_AUTHOR);
            mPrice = getArguments().getString(ARG_ITEM_PRICE);
            mDescription = getArguments().getString(ARG_ITEM_DESC);
            mPages = getArguments().getInt(ARG_ITEM_PAGES);
            mIsbn = getArguments().getString(ARG_ITEM_ISBN);
            mPublisher = getArguments().getString(ARG_ITEM_PUBLISHER);
            mPublishedDate = getArguments().getString(ARG_ITEMS_PUBLISHED_DATE);
            mPreviewLink = getArguments().getString(ARG_ITEMS_PREVIEW_LINK);
            mThumbnail = getArguments().getString(ARG_ITEMS_THUMBNAIL_IMAGE);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bsbook_detail, container, false);

        TextView noDataView = (TextView)v.findViewById(R.id.noDataPlaceholder);
        if(isDetailInfoAvailable){


            noDataView.setVisibility(View.GONE);


            TextView titleView = (TextView)v.findViewById(R.id.title);
            titleView.setText(mTitle);


            TextView prodView = (TextView)v.findViewById(R.id.google_preview_url);
            prodView.setText(mPreviewLink);

            TextView descView = (TextView)v.findViewById(R.id.description);
            descView.setText(mDescription);

            TextView authorView = (TextView)v.findViewById(R.id.author);
            authorView.setText(mAuthor);

            TextView isbnView = (TextView)v.findViewById(R.id.price);
            isbnView.setText(mPrice);

            ImageView imgView = (ImageView)v.findViewById(R.id.bookThumbnail);

            Glide.with(getContext())
                    .load(mThumbnail)
                    .into(imgView);

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
                        mHelper.addFavoriteBook(mIsbn, mTitle);
                        favBtn.setImageDrawable(getResources().getDrawable(R.drawable.btn_star_big_on));
                    }

                }
            });

        }
        else{
            noDataView.setVisibility(View.VISIBLE);
            LinearLayout layoutView = (LinearLayout) v.findViewById(R.id.detailitem);
            layoutView.setVisibility(View.GONE);
        }


      //  TextView rankView = (TextView)v.findViewById(R.id.rank);
       // rankView.setText(mRank);

       // TextView rankLastWeekView = (TextView)v.findViewById(R.id.rank_last_week);
       // rankLastWeekView.setText(mRank);



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
