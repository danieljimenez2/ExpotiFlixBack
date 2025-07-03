package io.reflectoring.Expotiflix.service;
import io.reflectoring.Expotiflix.model.album.AlbumResponseDTO;
import io.reflectoring.Expotiflix.model.album.SpotifyAlbumWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

}
