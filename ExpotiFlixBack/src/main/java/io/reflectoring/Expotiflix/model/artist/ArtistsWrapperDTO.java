package io.reflectoring.Expotiflix.model.artist;

import lombok.Data;

@Data
public class ArtistsWrapperDTO {
    private ArtistsResponseDTO artists;

    public ArtistsResponseDTO getArtists() {
        return artists;
    }
}

