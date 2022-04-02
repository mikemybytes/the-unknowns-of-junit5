package com.mikemybytes.junit5.http;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FooClient01Test {

    private final WireMockServer wireMockServer = new WireMockServer(
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

    @Test
    void getsFooFromRemoteServer() {
        System.out.println("Running test");

        // given
        var client = new FooClient(wireMockServer.baseUrl());
        wireMockServer.stubFor(
                WireMock.get(WireMock.urlEqualTo("/foo"))
                        .willReturn(WireMock.aResponse().withBody("bar"))
        );
        // when
        String foo = client.getFoo();
        // then
        assertThat(foo).isEqualTo("bar");
    }

}
