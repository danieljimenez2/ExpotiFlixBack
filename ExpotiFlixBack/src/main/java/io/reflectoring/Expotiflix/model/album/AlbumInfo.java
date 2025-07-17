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

    public String getAlbum_type() {
        return album_type;
    }

    public void setAlbum_type(String album_type) {
        this.album_type = album_type;
    }

    public int getTotal_tracks() {
        return total_tracks;
    }

    public void setTotal_tracks(int total_tracks) {
        this.total_tracks = total_tracks;
    }

    public List<String> getAvailable_markets() {
        return available_markets;
    }

    public void setAvailable_markets(List<String> available_markets) {
        this.available_markets = available_markets;
    }

    public ExternalUrlsDTO getExternal_urls() {
        return external_urls;
    }

    public void setExternal_urls(ExternalUrlsDTO external_urls) {
        this.external_urls = external_urls;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ImageDTO> getImages() {
        return images;
    }

    public void setImages(List<ImageDTO> images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getRelease_date_precision() {
        return release_date_precision;
    }

    public void setRelease_date_precision(String release_date_precision) {
        this.release_date_precision = release_date_precision;
    }

    public RestrictionsDTO getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(RestrictionsDTO restrictions) {
        this.restrictions = restrictions;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<ArtistDTO> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistDTO> artists) {
        this.artists = artists;
    }

    public TracksResponseDTO getTracks() {
        return tracks;
    }

    public void setTracks(TracksResponseDTO tracks) {
        this.tracks = tracks;
    }

    public List<CopyrightDTO> getCopyrights() {
        return copyrights;
    }

    public void setCopyrights(List<CopyrightDTO> copyrights) {
        this.copyrights = copyrights;
    }

    public Map<String, String> getExternal_ids() {
        return external_ids;
    }

    public void setExternal_ids(Map<String, String> external_ids) {
        this.external_ids = external_ids;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
}
