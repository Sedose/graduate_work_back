package com.example.work.ms_graph;

import com.microsoft.graph.authentication.IAuthenticationProvider;
import com.microsoft.graph.http.IHttpRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SimpleAuthProvider implements IAuthenticationProvider {

    private final String accessToken;

    @Override
    public void authenticateRequest(IHttpRequest request) {
        request.addHeader("Authorization", "Bearer " + accessToken);
    }
}
