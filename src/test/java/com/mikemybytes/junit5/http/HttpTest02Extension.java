package com.mikemybytes.junit5.http;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.extension.*;

class HttpTest02Extension implements BeforeAllCallback, BeforeEachCallback, AfterAllCallback, ParameterResolver {

    private static final WireMockServer wireMockServer = new WireMockServer(
            WireMockConfiguration.options().port(18081)
    );

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

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return isWireMockServerParam(parameterContext);
    }

    private boolean isWireMockServerParam(ParameterContext parameterContext) {
        return parameterContext.getParameter().getType().equals(WireMockServer.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        if (isWireMockServerParam(parameterContext)) {
            return wireMockServer;
        }
        return null;
    }
}
