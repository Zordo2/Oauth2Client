package com.oauth2client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ResourceServerClient {

    @Autowired
    private OAuth2AuthorizedClientManager authorizedClientManager;

    @Autowired
    private RestTemplate restTemplate;

    public String callResourceServer() {
        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId("resource-server")
                .principal("client-app")
                .build();

        String accessToken = authorizedClientManager.authorize(authorizeRequest)
                .getAccessToken().getTokenValue();

// Create headers with the access token
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        // Create an HTTP entity with the headers
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Make a request to the authorization server
        String apiEndpoint = "http://localhost:8080/private"; // Replace with your actual endpoint
        ResponseEntity<String> response = restTemplate.exchange(
                apiEndpoint,
                HttpMethod.GET,
                entity,
                String.class
        );

        return response.getBody();

    }
}
