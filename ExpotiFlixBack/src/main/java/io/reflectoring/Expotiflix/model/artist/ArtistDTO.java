package io.reflectoring.Expotiflix.model.artist;
import java.util.List;

import io.reflectoring.Expotiflix.model.generic.ExternalUrlsDTO;
import io.reflectoring.Expotiflix.model.generic.FollowersDTO;
import io.reflectoring.Expotiflix.model.generic.ImageDTO;
import lombok.Data;

@Data
public class ArtistDTO {
    private String id;
    private String name;
    private String href;
    private String type;
    private String uri;
    private int popularity;
    private List<String> genres;

    private ExternalUrlsDTO external_urls;
    private FollowersDTO followers;
    private List<ImageDTO> images;
}
