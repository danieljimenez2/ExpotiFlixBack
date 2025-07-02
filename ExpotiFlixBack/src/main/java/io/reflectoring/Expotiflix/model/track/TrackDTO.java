package io.reflectoring.Expotiflix.model.track;

import io.reflectoring.Expotiflix.model.album.AlbumDTO;
import io.reflectoring.Expotiflix.model.artist.ArtistDTO;
import io.reflectoring.Expotiflix.model.generic.ExternalUrlsDTO;
import lombok.Data;

import java.util.List;
import java.util.Map;


@Data
public class TrackDTO {

    private String id;
    private String name;

    private int duration_ms;
    private int track_number;
    private int disc_number;
    private int popularity;

    private boolean explicit;
    private boolean is_local;
    private boolean is_playable;

    private String href;
    private String type;
    private String uri;
    private String preview_url;
    private List<String> available_markets;
    private Map<String, String> external_ids;

    private ExternalUrlsDTO external_urls;
    private List<ArtistDTO> artists;
    private AlbumDTO album;
    private Boolean saved;
}
