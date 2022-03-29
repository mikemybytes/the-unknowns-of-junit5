package com.mikemybytes.junit5.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

class FooClient {

    private final String remoteServer;
    private final HttpClient client;

    FooClient(String remoteServer) {
        this.remoteServer = remoteServer;

        this.client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(1))
                .build();
    }

    String getFoo() {
        try {
            return getFooThrowing();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new IllegalStateException("Failed to get foo", e);
        }
    }

    String getFooThrowing() throws URISyntaxException, IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(new URI(remoteServer + "/foo"))
                .timeout(Duration.ofSeconds(3)) // always set timeouts!!!
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }


}
