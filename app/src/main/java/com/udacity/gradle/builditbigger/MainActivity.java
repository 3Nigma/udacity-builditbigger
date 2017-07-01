package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ro.tuscale.udacity.gradle.jokes.teller.JokeActivity;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
    private AlertDialog mInfoDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init dialogs
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage(getString(R.string.waiting_for_joke));

        mInfoDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.joke_not_available_title)
                .setMessage(R.string.joke_not_available_msg)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }

    public void tellJoke(View view) {
        final Context ctx = getApplicationContext();

        mProgressDialog.show();
        new TellJokeAsyncTask(ctx, new TellJokeAsyncTask.JokeLoadedCallback() {
            @Override
            public void onJokeLoaded(@NonNull String joke) {
                if (mProgressDialog != null && mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }

                if (joke != null) {
                    JokeActivity.startWithJoke(ctx, joke);
                } else {
                    // Joke is not available. It might be a backend issue
                    mInfoDialog.show();
                }
            }
        }).execute();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        if (mInfoDialog != null && mInfoDialog.isShowing()) {
            mInfoDialog.dismiss();
        }
    }
}
