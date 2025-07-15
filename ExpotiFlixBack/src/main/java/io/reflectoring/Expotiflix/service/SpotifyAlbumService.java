package io.reflectoring.Expotiflix.service;

import io.reflectoring.Expotiflix.model.album.AlbumInfo;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class SpotifyAlbumService {

    public AlbumInfo getAlbumInfo(String authorizationHeader, String albumId) {
        String url = "https://api.spotify.com/v1/albums/" + albumId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<AlbumInfo> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                AlbumInfo.class // CORRECTO: mapeamos a AlbumInfo
        );

        return response.getBody();
    }

    public boolean isAlbumFollowed(String authorizationHeader, String albumId) {
        String url = "https://api.spotify.com/v1/me/albums/contains?ids=" + albumId;

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

        return response.getBody()[0];
    }
}
