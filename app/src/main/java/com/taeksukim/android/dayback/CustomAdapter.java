package com.taeksukim.android.dayback;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.taeksukim.android.daybacklogin.R;

import java.util.ArrayList;

import static android.R.attr.data;

/**
 * Created by TaeksuKim on 2017. 4. 10..
 */

public class CustomAdapter extends FragmentStatePagerAdapter {


    public CustomAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
