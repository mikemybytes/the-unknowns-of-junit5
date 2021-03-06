package com.mikemybytes.junit5.http;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FooClient02Test extends BaseWireMockTest {

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
