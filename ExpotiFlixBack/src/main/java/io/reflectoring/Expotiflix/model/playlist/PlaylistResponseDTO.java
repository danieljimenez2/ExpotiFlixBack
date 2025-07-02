package io.reflectoring.Expotiflix.model.playlist;
import lombok.Data;

import java.util.List;

@Data
public class PlaylistResponseDTO {
    private String href;
    private List<PlaylistDTO> items;
    private int limit;
    private String next;
    private String previous;
    private int offset;
    private int total;
}
