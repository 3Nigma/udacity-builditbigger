package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import ro.tuscale.udacity.gradle.jokes.backend.myJokes.MyJokes;
import ro.tuscale.udacity.gradle.jokes.teller.JokeActivity;

public class TellJokeAsyncTask extends AsyncTask<Void, Void, String> {
    private JokeLoadedCallback mJokeLoadedClb;
    private static MyJokes myJokesService = null;
    private Context mContext;

    public TellJokeAsyncTask(@NonNull Context ctx, @NonNull JokeLoadedCallback jokeLoadedClb) {
        this.mContext = ctx;
        this.mJokeLoadedClb = jokeLoadedClb;
    }

    @Override
    protected String doInBackground(Void... params) {
        if (myJokesService == null) {
            MyJokes.Builder jokesBuilder = new MyJokes.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8081/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });

            myJokesService = jokesBuilder.build();
        }

        try {
            return myJokesService.tellAJoke().execute().getData();
        } catch (IOException ignored) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        mJokeLoadedClb.onJokeLoaded(joke);
    }

    public interface JokeLoadedCallback {
        void onJokeLoaded(@NonNull String joke);
    }
}
