package io.reflectoring.Expotiflix.model.album;

import io.reflectoring.Expotiflix.model.artist.ArtistDTO;
import io.reflectoring.Expotiflix.model.generic.CopyrightDTO;
import io.reflectoring.Expotiflix.model.generic.ExternalUrlsDTO;
import io.reflectoring.Expotiflix.model.generic.ImageDTO;
import io.reflectoring.Expotiflix.model.generic.RestrictionsDTO;
import io.reflectoring.Expotiflix.model.track.TrackDTO;
import io.reflectoring.Expotiflix.model.track.TrackWrapperDTO;
import io.reflectoring.Expotiflix.model.track.TracksInfoDTO;
import io.reflectoring.Expotiflix.model.track.TracksResponseDTO;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AlbumInfo {
    private String album_type;
    private int total_tracks;
    private List<String> available_markets;
    private ExternalUrlsDTO external_urls;
    private String href;
    private String id;
    private List<ImageDTO> images;
    private String name;
    private String release_date;
    private String release_date_precision;
    private RestrictionsDTO restrictions;
    private String type;
    private String uri;
    private List<ArtistDTO> artists;
    private TracksResponseDTO tracks;
    private List<CopyrightDTO> copyrights;
    private Map<String, String> external_ids;
    private List<String> genres;
    private String label;
    private int popularity;
}
