package io.reflectoring.Expotiflix.controller;

import io.reflectoring.Expotiflix.model.album.AlbumResponseDTO;
import io.reflectoring.Expotiflix.model.artist.ArtistDTO;
import io.reflectoring.Expotiflix.model.track.TrackDTO;
import io.reflectoring.Expotiflix.service.SpotifyArtistService;
import io.reflectoring.Expotiflix.service.SpotifyBrowseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    @Autowired
    private SpotifyArtistService artistService;

    @GetMapping("/info/{id}")
    public ArtistDTO getArtistInfo(@RequestHeader("Authorization") String authorizationHeader,
                                   @PathVariable String id)
    {
        return artistService.getArtistInfo(authorizationHeader,id);
    }
    @GetMapping("/info/{id}/top-tracks")
    public ResponseEntity<String> getTopTracks(@RequestHeader("Authorization") String authorizationHeader,
                                               @PathVariable String id) {
        String json = artistService.getTopTracksJson(authorizationHeader, id);
        return ResponseEntity.ok(json);
    }
    @GetMapping("/{id}/albums")
    public ResponseEntity<AlbumResponseDTO> getArtistAlbums(@RequestHeader("Authorization") String authorizationHeader,
                                                            @PathVariable String id) {
        AlbumResponseDTO albums = artistService.getArtistAlbums(authorizationHeader, id);
        return ResponseEntity.ok(albums);
    }
}
