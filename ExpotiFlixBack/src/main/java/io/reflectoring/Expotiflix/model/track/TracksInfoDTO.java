package io.reflectoring.Expotiflix.model.track;

import lombok.Data;

import java.util.List;

@Data
public class TracksInfoDTO {
    private String href;
    private List<TrackWrapperDTO> items;
    private int limit;
    private String next;
    private String previous;
    private int offset;
    private int total;

}
