package com.bignerdranch.android.recycolumbus;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ChangePasswordFragment extends AppCompatActivity {
    private EditText oldPass;
    private EditText newPass;
    private Button changePass;
    private String oldPassText = "";
    private String newPassText = "";
    //private Context context = getApplicationContext();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_change_password);

        oldPass = (EditText) findViewById(R.id.oldPassword);
        newPass = (EditText) findViewById(R.id.newPassword);
        changePass = (Button) findViewById(R.id.confirm);

        oldPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                oldPassText = oldPass.getText().toString();
            }
        });

        newPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                newPassText = newPass.getText().toString();
            }
        });

        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                AuthCredential crendential = EmailAuthProvider.getCredential(user.getEmail(),oldPassText);
                user.reauthenticate(crendential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                           user.updatePassword(newPassText).addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull Task<Void> task) {
                                   if (task.isSuccessful()){
                                       //Toast.makeText(context, "Password successfully changed", Toast.LENGTH_SHORT).show();
                                       Intent intent = new Intent(ChangePasswordFragment.this, SettingsActivity.class);
                                       startActivity(intent);
                                   }
                                   else{
                                       //Toast.makeText(context, "Error: Change password", Toast.LENGTH_SHORT).show();
                                   }
                               }
                           });
                        }
                        else{
                            //Toast.makeText(context, "Password is not correct", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

}
