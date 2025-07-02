package io.reflectoring.Expotiflix.model.track;

import lombok.Data;

import java.util.List;

@Data
public class TracksResponseDTO {
    private String href;
    private List<TrackDTO> items;
    private int limit;
    private String next;
    private String previous;
    private int offset;
    private int total;
}
