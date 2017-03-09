package com.yunikov.vertx.domain.system;

public class ApplicationTest {

    private String hostname;
    private String version;

    public ApplicationTest(final String hostname, final String version) {
        this.hostname = hostname;
        this.version = version;
    }

    public String getHostname() {
        return hostname;
    }

    public String getVersion() {
        return version;
    }
}
