package com.bignerdranch.android.recycolumbus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.webkit.WebView;

public class InformationActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_information:
                    mTextMessage.setText("InformationActivity");
                    return true;
                case R.id.navigation_location:
                    mTextMessage.setText("Location");
                    return true;
                case R.id.navigation_search:
                    Intent intent = new Intent(InformationActivity.this, SearchActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_settings:
                    mTextMessage.setText("Settings");
                    return true;
            }
            return false;
        }
    };

    public static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, InformationActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("https://www.columbus.gov/publicservice/RecyColumbus/");
    }

}
