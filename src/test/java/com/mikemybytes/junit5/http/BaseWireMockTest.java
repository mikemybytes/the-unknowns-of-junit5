package com.mikemybytes.junit5.http;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseWireMockTest {

    protected final WireMockServer wireMockServer = new WireMockServer(
            WireMockConfiguration.options().port(18081)
    );

    @BeforeAll
    void setUpAll() {
        if (!wireMockServer.isRunning()) {
            System.out.println("Starting WireMock server");
            wireMockServer.start();
        }
    }

    @BeforeEach
    void setUp() {
        System.out.println("Resetting WireMock server");
        wireMockServer.resetAll();
    }

    @AfterAll
    void tearDown() {
        if (wireMockServer.isRunning()) {
            System.out.println("Stopping WireMock server");
            wireMockServer.stop();
        }
    }

}
