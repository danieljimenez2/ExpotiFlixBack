package io.reflectoring.Expotiflix.model.track;

import io.reflectoring.Expotiflix.model.playlist.OwnerDTO;
import lombok.Data;


@Data
public class TrackWrapperDTO {
    private String added_at;
    private OwnerDTO added_by;
    private boolean is_local;
    private TrackDTO track;
}
