package com.bignerdranch.android.recycolumbus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AuthenticationActivity extends AppCompatActivity{

    private Button mLogInToggle;
    private Button mSignUpToggle;

    public static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, AuthenticationActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_authentication);

        mLogInToggle = findViewById(R.id.log_in_toggle);
        mSignUpToggle = findViewById(R.id.sign_up_toggle);

        FragmentManager fm = getSupportFragmentManager();
        Fragment authFragment = fm.findFragmentById(R.id.authentication_fragment_container);

        if(authFragment == null) {
            authFragment = new LogInFragment();
            fm.beginTransaction()
                    .add(R.id.authentication_fragment_container, authFragment)
                    .commit();
            mLogInToggle.setEnabled(false);
        }

        mLogInToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.authentication_fragment_container, new LogInFragment())
                        .commit();
                mLogInToggle.setEnabled(false);
                mSignUpToggle.setEnabled(true);
            }
        });

        mSignUpToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.authentication_fragment_container, new SignUpFragment())
                        .commit();
                mSignUpToggle.setEnabled(false);
                mLogInToggle.setEnabled(true);
            }
        });

    }
}
