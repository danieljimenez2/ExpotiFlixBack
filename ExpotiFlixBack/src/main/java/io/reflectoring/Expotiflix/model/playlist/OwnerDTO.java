package io.reflectoring.Expotiflix.model.playlist;

import io.reflectoring.Expotiflix.model.generic.ExternalUrlsDTO;
import lombok.Data;

@Data
public class OwnerDTO {
    private String display_name;
    private ExternalUrlsDTO external_urls;
    private String href;
    private String id;
    private String type;
    private String uri;
}
