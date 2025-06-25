package io.reflectoring.Expotiflix.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.reflectoring.Expotiflix.model.SpotifyProfileDTO;
import java.util.Collections;
@Service // Indica que esta clase es un servicio y puede ser inyectada con @Autowired
public class SpotifyService {

    public SpotifyProfileDTO getUserInfo(String authorizationHeader) {
        String url = "https://api.spotify.com/v1/me";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<SpotifyProfileDTO> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                SpotifyProfileDTO.class
        );

        return response.getBody();
    }
}