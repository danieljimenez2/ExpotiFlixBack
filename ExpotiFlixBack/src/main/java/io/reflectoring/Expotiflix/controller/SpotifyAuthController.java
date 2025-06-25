package io.reflectoring.Expotiflix.controller;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class SpotifyAuthController {
    private final String clientId = "76015d073195471d9659c8692e581274";
    private final String redirectUri = "http://localhost:8080/auth/callback";
    private final String codeVerifier = generateCodeVerifier();
    private String accessToken;

    @GetMapping("/login")
    public void login(HttpServletResponse response) throws IOException {
        String codeChallenge = generateCodeChallenge(codeVerifier);
        String state = UUID.randomUUID().toString();
        String url = "https://accounts.spotify.com/authorize" +
                "?response_type=code" +
                "&client_id=" + clientId +
                "&redirect_uri=" + URLEncoder.encode(redirectUri, StandardCharsets.UTF_8) +
                "&scope=user-read-private user-read-email" +
                "&state=" + state +
                "&code_challenge=" + codeChallenge +
                "&code_challenge_method=S256";

        response.sendRedirect(url);
    }

    @GetMapping("/callback")
    public ResponseEntity<String> callback (@RequestParam String code)
    {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("code", code);
        body.add("redirect_uri", redirectUri);
        body.add("client_id", clientId);
        body.add("code_verifier", codeVerifier);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(
                "https://accounts.spotify.com/api/token",
                request,
                Map.class
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            accessToken = (String) response.getBody().get("access_token");
            return ResponseEntity.ok("Token guardado en backend: " + accessToken);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al obtener token");
        }
    }
    // Métodos auxiliares

    private String generateCodeVerifier() {
        byte[] code = new byte[64];
        new SecureRandom().nextBytes(code);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(code);
    }

    private String generateCodeChallenge(String codeVerifier) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashed = digest.digest(codeVerifier.getBytes(StandardCharsets.US_ASCII));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hashed);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
