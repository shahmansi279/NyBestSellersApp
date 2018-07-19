package project.com.nybestsellerbooksapp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import project.com.nybestsellerbooksapp.fragment.BSFavoriteItemFragment;
import project.com.nybestsellerbooksapp.fragment.BSHistoryItemFragment;
import project.com.nybestsellerbooksapp.fragment.BSListItemFragment;

import java.util.HashMap;

/**
 * Created by Mansi on 1/14/18.
 */

public class BSFragmentPagerAdapter extends FragmentPagerAdapter
{

    private Context mContext;

    final private static String LISTS_TAG = "Lists";
    final private static String FAVORITES_TAG = "Favorites";
    final private static String HISTORY_TAG = "History";
    private HashMap<Integer, String> mFragmentTags;
    private FragmentManager mFragmentManager;

    public BSFragmentPagerAdapter(Context context, FragmentManager fm)
    {
        super(fm);
        mContext = context;
        mFragmentManager = fm;
        mFragmentTags = new HashMap<>();
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        Object obj = super.instantiateItem(container, position);
        if (obj instanceof Fragment) {
            // record the fragment tag here.
            Fragment f = (Fragment) obj;
            String tag = f.getTag();
            mFragmentTags.put(position, tag);
        }
        return obj;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return BSListItemFragment.newInstance(1);
        } else if (position == 1){
            return BSFavoriteItemFragment.newInstance(1);
        } else if(position == 2){
            return BSHistoryItemFragment.newInstance(1);
        }else {
            return BSListItemFragment.newInstance(1);
        }
    }

    public Fragment getFragment(int position) {
        String tag = mFragmentTags.get(position);
        if (tag == null)
            return null;
        return  mFragmentManager.findFragmentByTag(tag);
    }


    @Override
    public int getCount() {
        return 3;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return LISTS_TAG;
            case 1:
                return FAVORITES_TAG;
            case 2:
                return HISTORY_TAG;
            default:
                return null;
        }
    }

}
