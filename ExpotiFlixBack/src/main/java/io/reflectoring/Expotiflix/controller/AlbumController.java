package io.reflectoring.Expotiflix.controller;

import io.reflectoring.Expotiflix.model.album.AlbumInfo;
import io.reflectoring.Expotiflix.service.SpotifyAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private SpotifyAlbumService albumService;

    @GetMapping("/info/{id}")
    public ResponseEntity<AlbumInfo> getAlbumInfo(@RequestHeader("Authorization") String authorizationHeader,
                                                  @PathVariable String id) {
        AlbumInfo album = albumService.getAlbumInfo(authorizationHeader, id);
        return ResponseEntity.ok(album);
    }

    @GetMapping("/following")
    public ResponseEntity<Boolean> isAlbumFollowed(@RequestHeader("Authorization") String authorizationHeader,
                                                   @RequestParam String id) {
        boolean isFollowed = albumService.isAlbumFollowed(authorizationHeader, id);
        return ResponseEntity.ok(isFollowed);
    }
}
