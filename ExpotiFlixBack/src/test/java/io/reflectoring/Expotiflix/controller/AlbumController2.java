package io.reflectoring.Expotiflix.controller;

import io.reflectoring.Expotiflix.model.album.AlbumInfo;
import io.reflectoring.Expotiflix.service.AlbumService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/album2")
public class AlbumController2 {

    @Autowired
    private AlbumService2 albumService2;

    @GetMapping("/info")
    public ResponseEntity<AlbumInfo> getAlbumInfo() {
        AlbumInfo album = albumService2.getAlbumInfo();
        return ResponseEntity.ok(album);
    }
}
