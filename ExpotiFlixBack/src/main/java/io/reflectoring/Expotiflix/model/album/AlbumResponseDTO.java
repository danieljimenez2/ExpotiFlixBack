package io.reflectoring.Expotiflix.model.album;
import java.util.List;
import lombok.Data;


@Data
public class AlbumResponseDTO {
    private String href;
    private List<AlbumDTO> items;
    private int limit;
    private String next;
    private String previous;
    private int offset;
    private int total;
}
