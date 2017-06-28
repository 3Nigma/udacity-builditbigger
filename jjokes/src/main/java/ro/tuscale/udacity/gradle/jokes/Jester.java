package ro.tuscale.udacity.gradle.jokes;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class Jester {
    private static Jester mInstance = null;
    private List<String[]> mKnownJokes = null;

    private Jester() {
        CSVReader reader = new CSVReader(new InputStreamReader(Jester.class.getResourceAsStream("/shortjokes.csv")));

        try {
            // Go over the header and read all available jokes
            reader.readNext();
            mKnownJokes = reader.readAll();
        } catch (IOException e) {
            mKnownJokes = new ArrayList<>();
            System.err.println("There was an issue while loading the jokes: " + e.getMessage());
        }

        mKnownJokes.size();
    }

    public static Jester getInstance() {
        if (mInstance == null) {
            mInstance = new Jester();
        }

        return mInstance;
    }

    public int howManyJokesDoYouKnow() {
        return (mKnownJokes != null ? mKnownJokes.size() : 0);
    }

    public String tellJoke() {
        String returnedJoke;

        if (mKnownJokes == null || mKnownJokes.size() == 0) {
            returnedJoke = "Knock knock I'm out of ideas!";
        } else {
            int jokeIdToReturn = (int)(mKnownJokes.size() * Math.random());

            returnedJoke = mKnownJokes.get(jokeIdToReturn)[1];
        }

        return returnedJoke;
    }
}
