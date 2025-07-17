package io.reflectoring.Expotiflix.service;


import io.reflectoring.Expotiflix.model.album.AlbumInfo;
import org.springframework.stereotype.Service;
public class AlbumService2 {

    public AlbumInfo getAlbumInfo() {
        AlbumInfo album = new AlbumInfo();
        album.setName("Mock Album");
        return album;
    }
}
