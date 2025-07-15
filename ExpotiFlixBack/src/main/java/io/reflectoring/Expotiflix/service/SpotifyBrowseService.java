package io.reflectoring.Expotiflix.service;
import io.reflectoring.Expotiflix.model.album.AlbumResponseDTO;
import io.reflectoring.Expotiflix.model.album.SpotifyAlbumWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

@Service
public class SpotifyBrowseService {
    @Value("${spotify.api.url}")

    private String spotifyApiUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    public AlbumResponseDTO getNewReleases(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<SpotifyAlbumWrapper> response = restTemplate.exchange(
                spotifyApiUrl + "/browse/new-releases?limit=10",
                HttpMethod.GET,
                entity,
                SpotifyAlbumWrapper.class
        );

        return response.getBody().getAlbums();
    }

    public String searchAnything(String authorizationHeader, String query, String type) {
        String url = UriComponentsBuilder.fromHttpUrl("https://api.spotify.com/v1/search")
                .queryParam("q", query)
                .queryParam("type", type)
                .queryParam("market", "ES")
                .queryParam("limit", 15)
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );

        return response.getBody();
    }

}
