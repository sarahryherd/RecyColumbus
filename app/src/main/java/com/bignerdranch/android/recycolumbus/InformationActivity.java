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

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_information:
                    return true;
                case R.id.navigation_location:
                    startActivity(new Intent(InformationActivity.this, MapActivity.class));
                    return true;
                case R.id.navigation_search:
                    startActivity(new Intent(InformationActivity.this, SearchActivity.class));
                    return true;
                case R.id.navigation_settings:
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

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.getMenu().findItem(R.id.navigation_information).setChecked(true);

        WebView myWebView = findViewById(R.id.webview);
        myWebView.loadUrl("https://www.columbus.gov/publicservice/RecyColumbus/");
    }

}
