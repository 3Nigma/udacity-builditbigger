package ro.tuscale.udacity.gradle.jokes.teller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.TellJokeAsyncTask;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class TellLoadingInstrumentedTest {

    @Test
    public void tellJokeAsyncQuery() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        final CountDownLatch waitForResults = new CountDownLatch(1);

        new TellJokeAsyncTask(appContext, new TellJokeAsyncTask.JokeLoadedCallback() {
            @Override
            public void onJokeLoaded(@NonNull String joke) {
                assertTrue("Invalid joke returned.", joke.length() != 0);
                waitForResults.countDown();
            }
        }).execute();

        assertTrue("The backend did not respond in proper time.", waitForResults.await(10, TimeUnit.SECONDS));
    }
}
