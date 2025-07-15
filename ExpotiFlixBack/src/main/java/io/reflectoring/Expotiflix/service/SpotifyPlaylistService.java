package io.reflectoring.Expotiflix.service;

import io.reflectoring.Expotiflix.model.playlist.PlaylistDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class SpotifyPlaylistService {
    public PlaylistDTO getPlaylist(String authorizationHeader, String playlistid)
    {
        String url = "https://api.spotify.com/v1/playlists/" + playlistid;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<PlaylistDTO> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                PlaylistDTO.class
        );

        return response.getBody();
    }
    public boolean[] checkIfIFollowPlaylist(String authorizationHeader, String playlistId)
    {
        String url = "https://api.spotify.com/v1/playlists/"+playlistId+"/followers/contains";
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
