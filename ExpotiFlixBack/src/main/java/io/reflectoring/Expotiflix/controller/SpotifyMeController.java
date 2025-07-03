package io.reflectoring.Expotiflix.controller;

import io.reflectoring.Expotiflix.model.artist.ArtistDTO;
import io.reflectoring.Expotiflix.model.artist.ArtistsResponseDTO;
import io.reflectoring.Expotiflix.model.playlist.PlaylistResponseDTO;
import io.reflectoring.Expotiflix.model.track.SavedTracksResponse;
import io.reflectoring.Expotiflix.model.track.TrackDTO;
import io.reflectoring.Expotiflix.model.track.TracksResponseDTO;
import io.reflectoring.Expotiflix.service.SpotifyCheckFollowService;
import io.reflectoring.Expotiflix.service.SpotifyDeleteService;
import io.reflectoring.Expotiflix.service.SpotifyMeService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import io.reflectoring.Expotiflix.model.user.SpotifyProfileDTO;

@RestController // Indica que esta clase es un controlador REST (devuelve JSON directamente)
@RequestMapping("/me") // Define que todas las rutas dentro de este controlador comienzan con /me
public class SpotifyMeController {

    @Autowired //Autowired sirve para inyectar servicios en una clase
    private SpotifyMeService spotifyMeService;

    @Autowired
    private SpotifyCheckFollowService spotifyCheckService;

    @Autowired
    private SpotifyDeleteService spotifyDeleteService;

    @GetMapping("/profile")
    public ResponseEntity<SpotifyProfileDTO> getSpotifyUser(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        SpotifyProfileDTO userInfo = spotifyMeService.getUserInfo(authorizationHeader);
        return ResponseEntity.ok(userInfo);
    }
    @GetMapping("/top/{type}")
    public ResponseEntity<?> getTopItems(
            @PathVariable String type,
            @RequestHeader("Authorization") String authorizationHeader) {

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
        }

        if (type.equals("artists")) {
            var result = spotifyMeService.getTopItems(authorizationHeader, type, ArtistDTO.class);
            return ResponseEntity.ok(result);
        } else if (type.equals("tracks")) {
            var result = spotifyMeService.getTopItems(authorizationHeader, type, TrackDTO.class);
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body("Invalid type. Use 'artists' or 'tracks'.");
        }
    }
    @GetMapping("/followed-artists")
    public ResponseEntity<?> getFollowedArtists(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
        }

        ArtistsResponseDTO response = spotifyMeService.getFollowedArtists(authorizationHeader);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/playlists")
    public ResponseEntity<PlaylistResponseDTO> getUserPlaylists(@RequestHeader("Authorization") String authorizationHeader) {
        PlaylistResponseDTO response = spotifyMeService.getFollowedPlaylist(authorizationHeader);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/tracks")
    public ResponseEntity<?> getFollowedTracks(@RequestHeader("Authorization")String authorizationHeader)
    {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
        }
        SavedTracksResponse response = spotifyMeService.getFollowedTracks(authorizationHeader);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/tracks/contains")
    public ResponseEntity<?>checkIfTracksAreSaved
            (@RequestHeader("Authorization") String authorizationHeader,
             @RequestParam("ids") String ids)
    {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
        }
        boolean[] response = spotifyCheckService.checkIfTracksAreSaved(authorizationHeader, ids);
        return ResponseEntity.ok(response);
    }






}