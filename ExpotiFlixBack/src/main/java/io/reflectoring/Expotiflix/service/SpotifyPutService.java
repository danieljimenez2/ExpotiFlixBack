package io.reflectoring.Expotiflix.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class SpotifyPutService {
    public void saveTracks(String authorizationHeader, String ids) {
        String url = "https://api.spotify.com/v1/me/tracks?ids=" + ids;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(null, headers); // No body needed

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(url, HttpMethod.PUT, entity, Void.class);
    }
    public void followEntity(String authorizationHeader, String ids, String type) {
        String baseUrl = "https://api.spotify.com/v1";
        String url;
        HttpEntity<?> entity;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();

        switch (type) {
            case "tracks":
                url = baseUrl + "/me/tracks?ids=" + ids;
                entity = new HttpEntity<>(null, headers);
                break;

            case "albums":
                url = baseUrl + "/me/albums?ids=" + ids;
                entity = new HttpEntity<>(null, headers);
                break;

            case "artist":
            case "user":
                url = baseUrl + "/me/following?type=" + type;
                String[] idsArray = ids.split(",");
                StringBuilder sb = new StringBuilder();
                sb.append("{\"ids\":[");
                for (int i = 0; i < idsArray.length; i++) {
                    sb.append("\"").append(idsArray[i]).append("\"");
                    if (i < idsArray.length - 1) sb.append(",");
                }
                sb.append("]}");
                entity = new HttpEntity<>(sb.toString(), headers);
                break;

            case "playlists":
                String playlistId = ids.split(",")[0];
                url = baseUrl + "/playlists/" + playlistId + "/followers";
                entity = new HttpEntity<>("{}", headers);
                break;

            default:
                throw new IllegalArgumentException("Tipo no soportado: " + type);
        }

        restTemplate.exchange(url, HttpMethod.PUT, entity, Void.class);
    }
}
