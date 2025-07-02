package io.reflectoring.Expotiflix.model.playlist;

import io.reflectoring.Expotiflix.model.generic.ExternalUrlsDTO;
import io.reflectoring.Expotiflix.model.generic.ImageDTO;
import io.reflectoring.Expotiflix.model.track.TracksInfoDTO;
import lombok.Data;

import java.util.List;

@Data
public class PlaylistDTO {
    private boolean collaborative;
    private String description;
    private ExternalUrlsDTO external_urls;
    private String href;
    private String id;
    private String name;
    private String snapshot_id;
    private boolean isPublic;
    private String primary_color;
    private String type;
    private String uri;
    private OwnerDTO owner;
    private TracksInfoDTO tracks;
    private List<ImageDTO> images;
}
