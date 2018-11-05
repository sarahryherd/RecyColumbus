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

    //private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_information:
                    //mTextMessage.setText("Information");
                    return true;
                case R.id.navigation_location:
                    //mTextMessage.setText("Location");
                    startActivity(new Intent(InformationActivity.this, MapActivity.class));
                    return true;
                case R.id.navigation_search:
                    //mTextMessage.setText("Search");
                    startActivity(new Intent(InformationActivity.this, SearchActivity.class));
                    return true;
                case R.id.navigation_settings:
                    //mTextMessage.setText("Settings");
                    startActivity(new Intent(InformationActivity.this, SettingsActivity.class));
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

        //mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        WebView myWebView = findViewById(R.id.webview);
        myWebView.loadUrl("https://www.columbus.gov/publicservice/RecyColumbus/");
    }

}
