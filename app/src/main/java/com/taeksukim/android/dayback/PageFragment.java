package com.taeksukim.android.dayback;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taeksukim.android.daybacklogin.R;


/**
 * Created by TaeksuKim on 2017. 4. 12..
 */

class PageFragment extends Fragment{

    private int mPagerNumber;


    public static PageFragment create(int pageNumber) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt("page", pageNumber);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPagerNumber = getArguments().getInt("page");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        ViewGroup v = (ViewGroup)inflater.inflate(R.layout.fragment_page,container,false);
        v.findViewById(R.id.title);
        return v;

    }
}
