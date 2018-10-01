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

public class SignupFragment extends Fragment {

    private ToggleButton mLoginToggle;
    private ToggleButton mSignupToggle;
    private Button mSignupButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_signup, container, false);

        mLoginToggle = v.findViewById(R.id.signup_login_toggle);
        mLoginToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = LoginActivity.newIntent(getActivity());
                startActivity(loginIntent);
            }
        });

        mSignupToggle = v.findViewById(R.id.signup_signup_toggle);
        mSignupToggle.setEnabled(false);

        mSignupButton = v.findViewById(R.id.signup_signup_button);
        mSignupButton.setEnabled(false);

        return v;
    }
}
