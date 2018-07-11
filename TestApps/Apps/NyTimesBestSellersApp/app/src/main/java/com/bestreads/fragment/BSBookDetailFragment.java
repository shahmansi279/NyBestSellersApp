package com.bestreads.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import com.bestreads.R;
import com.bestreads.BSFavoritesDBHelper;
import com.bestreads.model.Item;
import com.bestreads.model.SaleInfo;
import com.bestreads.model.VolumeInfo;
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
    private static final String ARG_ITEMS_AVERAGE_RATING = "averageRating";

    final private static String GOOGLE_PREVIEW = "Google Preview Link";
    final private static String RATING_NOT_AVAILABLE = "Not Available";
    final private static String PRICE_NOT_AVAILABLE = "Not for Sale";
    final private static String GOOGLE_PREVIEW_NOT_AVAILABLE = "Google Preview Not Available";
    final private static String DEFAULT_PREVIEW_IMG = "default_preview";

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
    private String mAvgRating;

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
    public static BSBookDetailFragment newInstance(List<Item> bookDetailItem, String bookIdentifier) {

        BSBookDetailFragment fragment = new BSBookDetailFragment();
        Bundle args = new Bundle();

        if(bookDetailItem != null){

            VolumeInfo bookVolume = bookDetailItem.get(0).getVolumeInfo();
            SaleInfo bookSales = bookDetailItem.get(0).getSaleInfo();

            if(bookIdentifier != null)
                args.putString(ARG_ITEM_ISBN, bookIdentifier);
            else
                args.putString(ARG_ITEM_ISBN, bookIdentifier);

            args.putString(ARG_ITEM_TITLE,bookVolume.getTitle());
            args.putString(ARG_ITEM_DESC,bookVolume.getDescription());

            //combine authors

            StringBuilder authors =  new StringBuilder();
            for(String author: bookVolume.getAuthors()){
                authors.append(author);
                authors.append(" & ");

            }

            int index = authors.lastIndexOf("&");
            if(index != -1)
                authors.deleteCharAt(index);
            else{
                int firstIndex = authors.indexOf("&");
                if(firstIndex != -1)
                    authors.deleteCharAt(firstIndex);
            }


            args.putString(ARG_ITEM_AUTHOR,new String(authors).trim());
            args.putString(ARG_ITEM_PUBLISHER,bookVolume.getPublisher());

            if(bookSales.getRetailPrice() != null) {
                args.putString(ARG_ITEM_PRICE, bookSales.getRetailPrice().getAmount() + " " + bookSales.getRetailPrice().getCurrencyCode());
            } else {
                args.putString(ARG_ITEM_PRICE, "");
            }

            if(bookVolume.getPageCount() != null) {
                args.putInt(ARG_ITEM_PAGES, bookVolume.getPageCount());
            } else {
                args.putInt(ARG_ITEM_PAGES, 0);
            }


            if( bookVolume.getPreviewLink() != null){

                String link = "<a href='" + bookVolume.getPreviewLink() +  "'>" + GOOGLE_PREVIEW + "<a>";
                args.putString(ARG_ITEMS_PREVIEW_LINK, link);

            } else{
                args.putString(ARG_ITEMS_PREVIEW_LINK, GOOGLE_PREVIEW_NOT_AVAILABLE);
            }


            if(bookVolume.getPublishedDate() != null)
                args.putString(ARG_ITEMS_PUBLISHED_DATE, bookVolume.getPublishedDate());
            else
                args.putString(ARG_ITEMS_PUBLISHED_DATE, "");

            if(bookVolume.getPublisher() != null){
                args.putString(ARG_ITEM_PUBLISHER,bookVolume.getPublisher());
            } else {
                args.putString(ARG_ITEM_PUBLISHER, "");
            }


            if(bookVolume.getImageLinks().getThumbnail() != null)
                args.putString(ARG_ITEMS_THUMBNAIL_IMAGE, bookVolume.getImageLinks().getThumbnail());
            else
                args.putString(ARG_ITEMS_THUMBNAIL_IMAGE, DEFAULT_PREVIEW_IMG);

            if(bookVolume.getAverageRating() != null) {
                args.putString(ARG_ITEMS_AVERAGE_RATING, Double.toString(bookVolume.getAverageRating()));
            }
            else{
                args.putString(ARG_ITEMS_AVERAGE_RATING, RATING_NOT_AVAILABLE);
            }
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
            mAuthor =  getArguments().getString(ARG_ITEM_AUTHOR);
            mPrice = getArguments().getString(ARG_ITEM_PRICE);
            mDescription = getArguments().getString(ARG_ITEM_DESC);
            mPages = getArguments().getInt(ARG_ITEM_PAGES);
            mIsbn = getArguments().getString(ARG_ITEM_ISBN);
            mPublisher = getArguments().getString(ARG_ITEM_PUBLISHER);
            mPublishedDate = getArguments().getString(ARG_ITEMS_PUBLISHED_DATE);
            mPreviewLink = getArguments().getString(ARG_ITEMS_PREVIEW_LINK);
            mThumbnail = getArguments().getString(ARG_ITEMS_THUMBNAIL_IMAGE);
            mAvgRating = getArguments().getString(ARG_ITEMS_AVERAGE_RATING);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bsbook_detail, container, false);

        TextView noDataView = (TextView)v.findViewById(R.id.no_data_placeholder);
        ConstraintLayout cLayout = (ConstraintLayout)v.findViewById(R.id.content);
        if(isDetailInfoAvailable){

            noDataView.setVisibility(View.GONE);


            TextView titleView = (TextView)v.findViewById(R.id.title);
            titleView.setText(mTitle);

            TextView prodView = (TextView)v.findViewById(R.id.google_preview_url);
            prodView.setText(Html.fromHtml(mPreviewLink));
            Linkify.addLinks(prodView, Linkify.WEB_URLS);
            prodView.setMovementMethod(LinkMovementMethod.getInstance());

            TextView descView = (TextView)v.findViewById(R.id.description);
            descView.setText(mDescription);
            descView.setMovementMethod(new ScrollingMovementMethod());

            TextView authorView = (TextView)v.findViewById(R.id.author_lbl);
            authorView.setText(mAuthor);


            TextView priceView = (TextView)v.findViewById(R.id.price);
            TextView currencyView = (TextView)v.findViewById(R.id.price_currency);

            if(!mPrice.isEmpty()){
                priceView.setVisibility(View.VISIBLE);
                priceView.setText(mPrice);
                currencyView.setVisibility(View.VISIBLE);
            }
            else{
                priceView.setText(PRICE_NOT_AVAILABLE);
            }


            TextView publisherView = (TextView)v.findViewById(R.id.publisher);
            TextView publishedDateView = (TextView)v.findViewById(R.id.publishedDate);
            TextView publishedByView = (TextView)v.findViewById(R.id.publishedBy);
            TextView publishedOnView = (TextView)v.findViewById(R.id.publishedOn);

            if(!mPublisher.isEmpty()){


                publisherView.setVisibility(View.VISIBLE);
                publishedDateView.setVisibility(View.VISIBLE);
                publishedByView.setVisibility(View.VISIBLE);
                publishedOnView.setVisibility(View.VISIBLE);
                publisherView.setText(mPublisher);
                publishedDateView.setText(mPublishedDate);

            }
            else{
                publisherView.setVisibility(View.GONE);
                publishedDateView.setVisibility(View.GONE);
                publishedByView.setVisibility(View.GONE);
                publishedOnView.setVisibility(View.GONE);
            }


            TextView pagesView = (TextView)v.findViewById(R.id.pages);
            pagesView.setText(mPages.toString());

            TextView isbnView = (TextView)v.findViewById(R.id.isbn);
            isbnView.setText(mIsbn);


            ImageView imgView = (ImageView)v.findViewById(R.id.bookThumbNail);

            Glide.with(getContext())
                    .load(mThumbnail)
                    .into(imgView);

            final ImageButton favBtn = (ImageButton)v.findViewById(R.id.favButton);
            mHelper = new BSFavoritesDBHelper(getContext());


            boolean isFav = mHelper.isFavoriteBook(mIsbn);
            if(mHelper.isFavoriteBook(mIsbn))
                favBtn.setImageDrawable(getResources().getDrawable(R.drawable.fav));
            else
                favBtn.setImageDrawable(getResources().getDrawable(R.drawable.unfav));

            favBtn.setOnClickListener( new View.OnClickListener(){

                public void onClick(View v){

                    if(mHelper.isFavoriteBook(mIsbn)){

                        if(mHelper.removeFavoriteBook(mIsbn));
                            favBtn.setImageDrawable(getResources().getDrawable(R.drawable.unfav));

                    }else    {
                        if(mHelper.addFavoriteBook(mIsbn, mTitle, mAuthor));
                            favBtn.setImageDrawable(getResources().getDrawable(R.drawable.fav));
                    }

                }
            });

            TextView ratingView = (TextView)v.findViewById(R.id.average_rating);
            ImageView ratingStarView = (ImageView)v.findViewById(R.id.rating_img);
            ratingView.setText(mAvgRating);
            if(mAvgRating.equals(RATING_NOT_AVAILABLE))
            {
                ratingStarView.setVisibility(View.GONE);
            }
            else{
                ratingStarView.setVisibility(View.VISIBLE);
            }

        }
        else{
            cLayout.setVisibility(View.GONE);
            noDataView.setVisibility(View.VISIBLE);
        }

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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

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
