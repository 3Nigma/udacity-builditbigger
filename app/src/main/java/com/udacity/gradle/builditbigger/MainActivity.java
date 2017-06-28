package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ro.tuscale.udacity.gradle.jokes.teller.JokeActivity;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage(getString(R.string.waiting_for_joke));
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
                JokeActivity.startWithJoke(ctx, joke);
            }
        }).execute();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
