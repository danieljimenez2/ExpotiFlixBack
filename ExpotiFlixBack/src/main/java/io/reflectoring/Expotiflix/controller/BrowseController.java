package io.reflectoring.Expotiflix.controller;

import io.reflectoring.Expotiflix.model.album.AlbumResponseDTO;
import io.reflectoring.Expotiflix.service.SpotifyBrowseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController // Indica que esta clase es un controlador REST (devuelve JSON directamente)
@RequestMapping("/browse") // Define que todas las rutas dentro de este controlador comienzan con /spotify

public class BrowseController {
    @Autowired
    private SpotifyBrowseService spotifyBrowseService;

    @GetMapping("/new-releases")
    public ResponseEntity<AlbumResponseDTO> getNewReleases(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        AlbumResponseDTO albumResponse = spotifyBrowseService.getNewReleases(authorizationHeader);
        return ResponseEntity.ok(albumResponse);
    }

    @GetMapping("/search")
    public ResponseEntity<String> searchSpotify(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestParam("q") String query,
            @RequestParam("type") String type // puede ser album, artist, track
    ) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization header missing or invalid.");
        }

        try {
            String searchResult = spotifyBrowseService.searchAnything(authorizationHeader, query, type);
            return ResponseEntity.ok(searchResult);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error during search: " + e.getMessage());
        }
    }



}
