package io.reflectoring.Expotiflix.controller;

import io.reflectoring.Expotiflix.model.playlist.PlaylistDTO;
import io.reflectoring.Expotiflix.service.SpotifyPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    @Autowired
    private SpotifyPlaylistService playlistService;


    @GetMapping("/info/{id}")
    public ResponseEntity<?>getPlaylistInfo(
            @PathVariable String id,
            @RequestHeader("Authorization") String authorizationHeader)
    {
        PlaylistDTO playlistInfo = playlistService.getPlaylist(authorizationHeader,id);
        return ResponseEntity.ok(playlistInfo);
    }
    @GetMapping("{id}/followers/contains")
    public ResponseEntity<?>checkFollowedPlaylist
            (
                    @PathVariable String id,
                    @RequestHeader("Authorization") String authorizationHeader
            )
    {
        boolean[] laSigo = playlistService.checkIfIFollowPlaylist(authorizationHeader,id);
        return ResponseEntity.ok(laSigo);
    }

}
