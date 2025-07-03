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



}
