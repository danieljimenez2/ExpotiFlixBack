package io.reflectoring.Expotiflix.model.album;

import lombok.Data;

@Data
public class SpotifyAlbumWrapper {
    private AlbumResponseDTO albums;

    public AlbumResponseDTO getAlbums() {
        return this.albums;
    }
}
