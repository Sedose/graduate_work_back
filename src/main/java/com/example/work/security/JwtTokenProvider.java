package com.example.work.security;

import com.example.work.entity.UserEntity;
import com.example.work.ms_graph.SimpleAuthProvider;
import com.microsoft.graph.models.extensions.User;
import com.microsoft.graph.requests.extensions.GraphServiceClient;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Qualifier("userDetailsServiceImpl")
    private final UserDetailsService userDetailsService;

    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private Long validityInInMilliseconds;
    @Value("${jwt.header}")
    private String authHeader;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(UserEntity userEntity) {
        Claims claims = Jwts.claims().setSubject(userEntity.getEmail());
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInInMilliseconds * 1000);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String accessToken) {
        var user = retrieveUser(accessToken);
        var userDetails = userDetailsService.loadUserByUsername(user.mail);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String getRole(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("role", String.class);
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
                .select("displayName,mailboxSettings")
                .get();
    }
}
