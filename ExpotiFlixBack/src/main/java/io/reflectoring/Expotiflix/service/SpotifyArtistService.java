package io.reflectoring.Expotiflix.service;
import io.reflectoring.Expotiflix.model.album.AlbumResponseDTO;
import io.reflectoring.Expotiflix.model.artist.ArtistDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class SpotifyArtistService {

    public ArtistDTO getArtistInfo(String authorizationHeader, String artistId) {
        String url = "https://api.spotify.com/v1/artists/" + artistId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ArtistDTO> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                ArtistDTO.class
        );

        return response.getBody();
    }
    public String getTopTracksJson(String authorizationHeader, String artistId) {
        String url = "https://api.spotify.com/v1/artists/" + artistId + "/top-tracks?market=ES";

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
    public AlbumResponseDTO getArtistAlbums(String authorizationHeader, String artistId) {
        String url = "https://api.spotify.com/v1/artists/" + artistId +
                "/albums?include_groups=album,single,appears_on,compilation&market=ES&limit=15";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<AlbumResponseDTO> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                AlbumResponseDTO.class
        );

        return response.getBody();
    }
}
