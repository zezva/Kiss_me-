package com.example.zezva.kiss_me;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    // UI references.
    private EditText mEditTextName;
    private EditText mEditTextGender;
    private View mProgressView;
    private View mLoginFormView;

    private  ClientThread clientThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEditTextName = (EditText) findViewById(R.id.editTextName);


        mEditTextGender = (EditText) findViewById(R.id.editTextGender);
        try {
            clientThread = new ClientThread();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Thread thread = new Thread(clientThread);
        thread.start();


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
            showProgress(true);

            Intent intent   = new Intent(LoginActivity.this, RoomActivity.class);
            Client client = new Client(name, gender);

           clientThread.sendNewClient(client);

            intent.putExtra("client", client);
            intent.putExtra("clientThread", clientThread);
            startActivity(intent);
            showProgress(false);


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

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);

        }
    }

}

