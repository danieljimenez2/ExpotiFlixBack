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
import io.reflectoring.Expotiflix.service.SpotifyPutService;
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

    @Autowired
    private SpotifyPutService spotifyPutService;

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
    //Recoger las canciones que tengo guardadas
    @GetMapping("/tracks")
    public ResponseEntity<?> getFollowedTracks(@RequestHeader("Authorization")String authorizationHeader)
    {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
        }
        SavedTracksResponse response = spotifyMeService.getFollowedTracks(authorizationHeader);
        return ResponseEntity.ok(response);
    }
    //Check Si hay canciones guardadas
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
    //Borrar canciones
    @DeleteMapping("/tracks")
    public ResponseEntity<?> removeSavedTracks(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestParam("ids") String ids) {

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
        }

        try {
            spotifyDeleteService.deleteSavedTracks(authorizationHeader, ids);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to remove tracks: " + e.getMessage());
        }
    }
    @PutMapping("/tracks")
    public ResponseEntity<?> saveTracks(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestParam("ids") String ids
    ) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
        }

        try {
            spotifyPutService.saveTracks(authorizationHeader, ids);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to save tracks: " + e.getMessage());
        }
    }
    @GetMapping("/following")
    public ResponseEntity<?> checkIfFollowing(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestParam("type") String type,
            @RequestParam("ids") String ids
    ) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
        }

        if (!type.equals("artist") && !type.equals("user")) {
            return ResponseEntity.badRequest().body("Invalid type. Use 'artist' or 'user'.");
        }
        try {
            boolean[] response = spotifyCheckService.checkIfArtistOrUserAreFollowed(authorizationHeader, ids, type);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error checking follow status: " + e.getMessage());
        }
    }
    // Guardar o seguir cualquier tipo de entidad (track, album, artist, user, playlist)
    @PutMapping("/follow")
    public ResponseEntity<?> followEntity(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestParam("type") String type,
            @RequestParam("ids") String ids
    ) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
        }

        try {
            spotifyPutService.followEntity(authorizationHeader, ids, type);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to follow: " + e.getMessage());
        }
    }

    // Dejar de seguir o eliminar cualquier tipo de entidad
    @DeleteMapping("/unfollow")
    public ResponseEntity<?> unfollowEntity(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestParam("type") String type,
            @RequestParam("ids") String ids
    ) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
        }

        try {
            spotifyDeleteService.unfollowEntity(authorizationHeader, ids, type);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to unfollow: " + e.getMessage());
        }
    }



    }