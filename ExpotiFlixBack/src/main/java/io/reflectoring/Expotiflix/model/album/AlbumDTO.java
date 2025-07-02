package io.reflectoring.Expotiflix.model.album;
import io.reflectoring.Expotiflix.model.artist.ArtistDTO;
import io.reflectoring.Expotiflix.model.generic.ExternalUrlsDTO;
import io.reflectoring.Expotiflix.model.generic.ImageDTO;
import lombok.Data;
import java.util.List;

@Data
public class AlbumDTO {
    private String id;
    private String name;
    private String release_date;
    private String album_type;
    private List<String> available_markets;
    private List<ArtistDTO> artists;
    private List<ImageDTO> images;
    private ExternalUrlsDTO external_urls;
    private String href;
    private int total_tracks;
    private String type;
    private String uri;
}
