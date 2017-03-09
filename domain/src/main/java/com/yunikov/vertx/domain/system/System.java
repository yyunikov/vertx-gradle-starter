package com.yunikov.vertx.domain.system;

import com.yunikov.vertx.domain.configs.GradleProperties;
import io.vertx.core.Future;
import io.vertx.core.VertxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class System {

    private final GradleProperties gradleProperties;

    public System(final GradleProperties gradleProperties) {
        this.gradleProperties = gradleProperties;
    }

    public Future<ApplicationTest> applicationTest() {
        return gradleProperties.version().map(version -> {
                try {
                    return new ApplicationTest(hostname(), version);
                } catch (final IOException e) {
                    throw new VertxException(e);
                }
            });
    }

    private String hostname() throws IOException {
        final String hostname = java.lang.System.getenv("HOSTNAME");

        if (hostname == null) {
            final Process p = Runtime.getRuntime().exec("hostname");
            final BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            final StringBuilder outputBuilder = new StringBuilder();

            String line;
            while ((line = in.readLine()) != null) {
                outputBuilder.append(line);
            }

            // Returning output from executing 'hostname' command
            return outputBuilder.toString();
        }

        // Returning hostname from the HOSTNAME environment variable
        return hostname;
    }
}
