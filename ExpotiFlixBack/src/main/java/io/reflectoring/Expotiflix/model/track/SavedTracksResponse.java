package io.reflectoring.Expotiflix.model.track;

import lombok.Data;

import java.util.List;

@Data
public class SavedTracksResponse {
    private String href;
    private List<SavedTracksDTO> items;
    private int limit;
    private String next;
    private String previous;
    private int offset;
    private int total;
}
