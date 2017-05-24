package com.gashe.goodmorningbaby;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

/**
 * Created by Gashe on 22/5/17.
 */

public class MyPageAdapter extends FragmentStatePagerAdapter {

    public MyPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position){
            case 0: fragment = new LastPictureFragment();
                break;
            case 1: fragment = new HistorialFragment();
                break;
            case 2: fragment = new SettingFragment();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }



    @Override
    public CharSequence getPageTitle(int position) {
        String response = "";

        switch (position){
            case 0: response = "ÚLTIMA FOTO";
                break;
            case 1: response = "HISTORIAL";
                break;
            case 2: response = "AJUSTES";
        }

        return response;


    }




}
