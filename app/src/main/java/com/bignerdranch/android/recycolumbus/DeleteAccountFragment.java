package com.bignerdranch.android.recycolumbus;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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

public class DeleteAccountFragment extends AppCompatActivity {
    private EditText password;
    private String passwordText = "";
    private Button changePassword;
    private TextView plainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account_fragment);

        password = (EditText) findViewById(R.id.password);
        plainText = (TextView) findViewById(R.id.plainText);
        plainText.setFocusable(false);
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                passwordText = password.getText().toString();
            }
        });

        changePassword = (Button) findViewById(R.id.deleteButt);
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(DeleteAccountFragment.this);
                alert.setTitle("Delete Account");
                alert.setMessage("Are you sure you want to delete the account?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        AuthCredential crendential = EmailAuthProvider.getCredential(user.getEmail(),passwordText);
                        user.reauthenticate(crendential).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Context context = getApplicationContext();
                                            Toast.makeText(context, "User has been deleted", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(DeleteAccountFragment.this, HomePageActivity.class);
                                            startActivity(intent);
                                        }
                                    });
                                }
                                else{
                                    Context context = getApplicationContext();
                                    CharSequence text = "The password you entered is not correct";
                                    int duration = Toast.LENGTH_SHORT;

                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                }
                            }
                        });

                        dialog.dismiss();
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog temp = alert.create();
                temp.show();
            }
        });
    }
}
