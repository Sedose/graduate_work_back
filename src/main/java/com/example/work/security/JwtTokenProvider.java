package com.example.work.security;

import com.microsoft.graph.models.extensions.User;
import com.microsoft.graph.requests.extensions.GraphServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Qualifier("userDetailsServiceImpl")
    private final UserDetailsService userDetailsService;

    @Value("${jwt.header}")
    private String authHeader;

    public Authentication getAuthentication(String accessToken) {
        var user = retrieveUser(accessToken);
        var userDetails = userDetailsService.loadUserByUsername(user.mail);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(authHeader);
    }

    private User retrieveUser(String accessToken) {
        return GraphServiceClient.builder()
                .authenticationProvider(new SimpleAuthProvider(accessToken))
                .buildClient()
                .me()
                .buildRequest()
                .select("mail")
                .get();
    }
}
