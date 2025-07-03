package io.reflectoring.Expotiflix.model.generic;

import java.util.List;
import lombok.Data;

@Data
public class TopItemsResponseDTO<T>{
    private String href;
    private List<T> items;
    private int limit;
    private int offset;
    private int total;
    private String next;
    private String previous;
}
