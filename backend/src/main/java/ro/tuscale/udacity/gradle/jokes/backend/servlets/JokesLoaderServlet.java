package ro.tuscale.udacity.gradle.jokes.backend.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import ro.tuscale.udacity.gradle.jokes.Jester;

public class JokesLoaderServlet implements Servlet {
    private ServletConfig mConfig;
    @Override
    public void init(ServletConfig config) throws ServletException {
        this.mConfig = config;

        System.out.println("Loading jester jokes ...");
        System.out.println(String.format("Done loading %d jokes. Let the fun begin!", Jester.getInstance().howManyJokesDoYouKnow()));
    }

    @Override
    public ServletConfig getServletConfig() {
        return mConfig;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        // No-op
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        // No-op
    }
}
