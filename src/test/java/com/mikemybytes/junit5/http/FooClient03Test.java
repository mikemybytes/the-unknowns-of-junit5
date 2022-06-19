package com.mikemybytes.junit5.http;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(HttpTest01Extension.class) // you can apply multiple @ExtendWith
class FooClient03Test {

    @Test
    void getsFooFromRemoteServer() {
        System.out.println("Running test");

        // given
        WireMockServer wireMock = HttpTest01Extension.wireMock(); // TODO: is there a better way? (no static call)

        var client = new FooClient(wireMock.baseUrl());
        wireMock.stubFor(
                WireMock.get(WireMock.urlEqualTo("/foo"))
                        .willReturn(WireMock.aResponse().withBody("bar"))
        );
        // when
        String foo = client.getFoo();
        // then
        assertThat(foo).isEqualTo("bar");
    }

}
