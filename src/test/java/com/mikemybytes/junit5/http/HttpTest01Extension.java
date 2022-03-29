package com.mikemybytes.junit5.http;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.extension.*;

class HttpTest01Extension implements BeforeAllCallback, BeforeEachCallback, AfterAllCallback {

    private static final WireMockServer wireMockServer = new WireMockServer(
            WireMockConfiguration.options().port(18081)
    );

    static WireMockServer wireMock() {
        return wireMockServer;
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        if (!wireMockServer.isRunning()) {
            System.out.println("Starting WireMock server");
            wireMockServer.start();
        }
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        System.out.println("Resetting WireMock server");
        wireMockServer.resetAll();
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) {
        if (wireMockServer.isRunning()) {
            System.out.println("Stopping WireMock server");
            wireMockServer.stop();
        }
    }

}
