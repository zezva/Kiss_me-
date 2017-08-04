package com.example.zezva.kiss_me.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.zezva.kiss_me.model.Client;
import com.example.zezva.kiss_me.R;
import com.example.zezva.kiss_me.udpconnection.CommandSender;
import com.example.zezva.kiss_me.udpconnection.EventReceiver;

import java.net.SocketException;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    // UI references.
    private EditText mEditTextName;
    private EditText mEditTextGender;
    private View mProgressView;
    private View mLoginFormView;

    private CommandSender eventSender;
    private EventReceiver eventReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEditTextName = (EditText) findViewById(R.id.editTextName);


        mEditTextGender = (EditText) findViewById(R.id.editTextGender);

        try {
            eventReceiver = new EventReceiver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        eventReceiver.start();


        Button mEmailSignInButton = (Button) findViewById(R.id.sign_in);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    attemptLogin();
                } catch (SocketException e) {
                    e.printStackTrace();
                }  catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }





    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() throws Exception {


        // Reset errors.
        mEditTextName.setError(null);
        mEditTextGender.setError(null);

        // Store values at the time of the login attempt.
        String name = mEditTextName.getText().toString();
        String gender = mEditTextGender.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(gender) && !isGenderValid(gender)) {
            mEditTextGender.setError(getString(R.string.invalidGender));
            focusView = mEditTextGender;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(name)) {
            mEditTextName.setError(getString(R.string.error_field_required));
            focusView = mEditTextName;
            cancel = true;
        } else if (!isNameValid(name)) {
            mEditTextName.setError(getString(R.string.longName));
            focusView = mEditTextName;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.


            Intent intent   = new Intent(LoginActivity.this, RoomActivity.class);
            Client client = new Client(name, gender);

            eventSender = new CommandSender();
            eventSender.execute(client);

            intent.putExtra("client", client);
           intent.putExtra("eventReceiver", eventReceiver);
            startActivity(intent);



        }
    }

    private boolean isNameValid(String name) {
        //TODO: Replace this with your own logic
        return name.length() < 20;
    }

    private boolean isGenderValid(String gender) {
        //TODO: Replace this with your own logic
        return  gender.equals("Male") ||
                gender.equals("male") ||
                gender.equals("female") ||
                gender.equals("Female");
    }



}

