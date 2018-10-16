package com.bignerdranch.android.recycolumbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ToggleButton;

public class LoginFragment extends Fragment {

    private ToggleButton mLoginToggle;
    private ToggleButton mSignupToggle;
    private Button mLoginButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        mLoginToggle = v.findViewById(R.id.login_login_toggle);
        mLoginToggle.setEnabled(false);

        mSignupToggle = v.findViewById(R.id.login_signup_toggle);
        mSignupToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signupIntent = SignupActivity.newIntent(getActivity());
                startActivity(signupIntent);
            }
        });

        mLoginButton = v.findViewById(R.id.login_login_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent infoIntent = InformationActivity.newIntent(getActivity());
                startActivity(infoIntent);
            }
        });

        return v;
    }
}
