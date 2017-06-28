package ro.tuscale.udacity.gradle.jokes.teller;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class JokeActivity extends AppCompatActivity {
    private static final String JOKE_TO_TELL_KEY = "ro.tuscale.udacity.gradle.jokes.teller.JOKE_TO_TELL_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Intent startupIntent = getIntent();

        if (startupIntent != null) {
            String jokeToTell = startupIntent.getStringExtra(JOKE_TO_TELL_KEY);

            if (jokeToTell != null) {
                TextView jokeHolder = (TextView) findViewById(R.id.activity_main_tv_joke);

                jokeHolder.setText(jokeToTell);
            } else {
                // Started by an intent, but not the right one
                finish();
            }
        } else {
            // Nothing else to do but exit
            finish();
        }
    }

    public static void startWithJoke(@NonNull Context ctx, @NonNull String joke) {
        Intent activityStartupIntent = new Intent(ctx, JokeActivity.class);

        activityStartupIntent.putExtra(JOKE_TO_TELL_KEY, joke);
        ctx.startActivity(activityStartupIntent);
    }

    public void btDislikedIt(View view) {
        Toast.makeText(this, R.string.meah_sentiment_toast, Toast.LENGTH_SHORT).show();
        finish();
    }

    public void btLikedIt(View view) {
        Toast.makeText(this, R.string.haha_sentiment_toast, Toast.LENGTH_SHORT).show();
        finish();
    }
}
