package com.bignerdranch.android.recycolumbus;

import android.support.v4.app.Fragment;

public class SearchActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new SearchFragment();
    }

}
