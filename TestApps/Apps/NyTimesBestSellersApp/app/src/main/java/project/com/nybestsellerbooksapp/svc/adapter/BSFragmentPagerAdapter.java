package project.com.nybestsellerbooksapp.svc.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import project.com.nybestsellerbooksapp.svc.fragment.BSFavoriteItemFragment;
import project.com.nybestsellerbooksapp.svc.fragment.BSHistoryItemFragment;
import project.com.nybestsellerbooksapp.svc.fragment.BSListItemFragment;

/**
 * Created by Mansi on 1/14/18.
 */

public class BSFragmentPagerAdapter extends FragmentPagerAdapter
{

    private Context mContext;

    public BSFragmentPagerAdapter(Context context, FragmentManager fm)
    {
        super(fm);
        mContext = context;
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
                return "Best Seller";
            case 1:
                return "Favorites";
            case 2:
                return "History";
            default:
                return null;
        }
    }

}
