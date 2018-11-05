package com.bignerdranch.android.recycolumbus;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import static android.app.PendingIntent.getActivity;

public class SettingsActivity extends AppCompatActivity {

    private Button delete_button;
    private Button changePass_button;
    private Button logout_button;
    private TextView emailText;
    private TextView nameText;

    public static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, SettingsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        delete_button = (Button) findViewById(R.id.deleteAcctButt);
        changePass_button = (Button) findViewById(R.id.changePassBut);
        logout_button = (Button) findViewById(R.id.logoutBut);
        emailText = (TextView) findViewById(R.id.emailText);
        emailText.setFocusable(false);
        nameText = (TextView) findViewById(R.id.nameText);

        //Get user's email from firebase
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String email = user.getEmail();
        final String password;
        String name = user.getDisplayName();
        emailText.setText(email);
        nameText.setText(name);

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, DeleteAccountFragment.class);
                startActivity(intent);
            }
        });

        changePass_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, ChangePasswordFragment.class);
                startActivity(intent);
            }
        });
        
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(SettingsActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });
    }



}
