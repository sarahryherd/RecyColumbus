package com.bignerdranch.android.recycolumbus;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class LogInActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, LogInActivity.class);
    }

    @Override
    protected Fragment createFragment() {
        return new LogInFragment();
    }
}
