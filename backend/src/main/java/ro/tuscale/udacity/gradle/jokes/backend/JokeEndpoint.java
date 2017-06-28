package ro.tuscale.udacity.gradle.jokes.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import ro.tuscale.udacity.gradle.jokes.Jester;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myJokes",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.jokes.gradle.udacity.tuscale.ro",
                ownerName = "backend.jokes.gradle.udacity.tuscale.ro",
                packagePath = ""
        )
)
public class JokeEndpoint {

    /**
     * Tells a joke! Really funny stuff :D
     *
     * @return all the time something funny &, hopefully, fresh
     */
    @ApiMethod(name = "tellAJoke")
    public JokeBean tellAJoke() {
        JokeBean response = new JokeBean();

        response.setData(Jester.getInstance().tellJoke());

        return response;
    }

}
