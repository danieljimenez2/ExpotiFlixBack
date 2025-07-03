package io.reflectoring.Expotiflix.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class SpotifyDeleteService {
    public void deleteSavedTracks(String authorizationHeader, String ids) {
        String url = "https://api.spotify.com/v1/me/tracks?ids=" + ids;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.exchange(url, HttpMethod.DELETE, entity, Void.class);
    }
}
