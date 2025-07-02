package io.reflectoring.Expotiflix.model.artist;
import lombok.Data;
import java.util.List;

@Data
public class ArtistsResponseDTO {
        private String href;
        private List<ArtistDTO> items;
        private int limit;
        private String next;
        private String previous;
        private int total;
}
