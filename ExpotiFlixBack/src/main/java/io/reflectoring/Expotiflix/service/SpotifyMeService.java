package io.reflectoring.Expotiflix.service;

import io.reflectoring.Expotiflix.model.artist.ArtistsResponseDTO;
import io.reflectoring.Expotiflix.model.artist.ArtistsWrapperDTO;
import io.reflectoring.Expotiflix.model.generic.TopItemsResponseDTO;
import io.reflectoring.Expotiflix.model.playlist.PlaylistResponseDTO;
import io.reflectoring.Expotiflix.model.track.SavedTracksResponse;
import io.reflectoring.Expotiflix.model.track.TracksResponseDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.reflectoring.Expotiflix.model.user.SpotifyProfileDTO;
import java.util.Collections;
@Service // Indica que esta clase es un servicio y puede ser inyectada con @Autowired
public class SpotifyMeService {
    //Recoger la información de mi usuario
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
    //Recoger las canciones mas escuchadas
    public <T> TopItemsResponseDTO<T> getTopItems(String authorizationHeader, String type, Class<T> itemType) {
        String url = "https://api.spotify.com/v1/me/top/" + type + "?limit=20";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        // Usamos ParameterizedTypeReference para manejar tipos genéricos
        ParameterizedTypeReference<TopItemsResponseDTO<T>> responseType =
                new ParameterizedTypeReference<TopItemsResponseDTO<T>>() {};

        ResponseEntity<TopItemsResponseDTO<T>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                responseType
        );

        return response.getBody();
    }
    //Recoger los artistas que sigo
    public ArtistsResponseDTO getFollowedArtists(String authorizationHeader)
    {
        String url = "https://api.spotify.com/v1/me/following?type=artist";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ArtistsWrapperDTO> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                ArtistsWrapperDTO.class
        );

        return response.getBody().getArtists();
    }
    //Recoger Mis Playlists seguidas
    public PlaylistResponseDTO getFollowedPlaylist(String authorizationHeader)
    {
        String url="https://api.spotify.com/v1/me/playlists";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<PlaylistResponseDTO> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                PlaylistResponseDTO.class
        );

        return response.getBody();
    }
    public SavedTracksResponse getFollowedTracks(String authorizationHeader)
    {
        String url="https://api.spotify.com/v1/me/tracks";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",authorizationHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<SavedTracksResponse> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
                SavedTracksResponse.class
        );
        return response.getBody();
    }
}