package com.mikemybytes.junit5.http;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(HttpTest02Extension.class)
class FooClient04Test {

    @Test
    void getsFooFromRemoteServer(WireMockServer wireMock) { // injected by the extension!
        System.out.println("Running test");

        // given
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
