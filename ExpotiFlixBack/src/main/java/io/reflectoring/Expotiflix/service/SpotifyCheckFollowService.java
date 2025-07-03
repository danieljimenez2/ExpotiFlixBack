package io.reflectoring.Expotiflix.service;


import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class SpotifyCheckFollowService {
    public boolean[] checkIfTracksAreSaved(String authorizationHeader, String ids) {
        String url = "https://api.spotify.com/v1/me/tracks/contains?ids=" + ids;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<boolean[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                boolean[].class
        );

        return response.getBody();
    }

}
