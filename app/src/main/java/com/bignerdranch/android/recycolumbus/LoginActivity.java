package com.bignerdranch.android.recycolumbus;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class LoginActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, LoginActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new LoginFragment();
    }
}
