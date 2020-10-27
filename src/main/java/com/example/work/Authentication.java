package com.example.work;

import com.microsoft.aad.msal4j.DeviceCode;
import com.microsoft.aad.msal4j.DeviceCodeFlowParameters;
import com.microsoft.aad.msal4j.IAuthenticationResult;
import com.microsoft.aad.msal4j.PublicClientApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

@Slf4j
@Component
public class Authentication {

    private final static String authority = "https://login.microsoftonline.com/common/";

    @Value("${app.id}")
    private String applicationId;

    @Value("${app.scopes}")
    private Set<String> scopes;

    public Optional<String> getUserAccessToken() {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        PublicClientApplication app;
        Optional<String> resultToken = Optional.empty();
        try {
            app = PublicClientApplication.builder(applicationId)
                    .authority(authority)
                    .executorService(pool)
                    .build();
            Consumer<DeviceCode> deviceCodeConsumer = deviceCode ->
                    System.out.println(deviceCode.message());
            IAuthenticationResult result = app.acquireToken(
                    DeviceCodeFlowParameters
                            .builder(scopes, deviceCodeConsumer)
                            .build()
            ).exceptionally(exception -> {
                System.out.println("Unable to authenticate: " + exception.getMessage());
                return null;
            }).join();
            pool.shutdown();
            if (result != null) {
                resultToken = Optional.ofNullable(result.accessToken());
            }
        } catch (MalformedURLException exception) {
            log.debug("Exception when getting an access token: " + exception.getMessage());
        }
        return resultToken;
    }
}
