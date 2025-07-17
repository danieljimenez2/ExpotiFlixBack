package io.reflectoring.Expotiflix.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Busqueda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String query;

    private String tipo;

    private LocalDateTime timestamp;

    public Busqueda() {}

    public Busqueda(String query, String tipo, LocalDateTime timestamp) {
        this.query = query;
        this.tipo = tipo;
        this.timestamp = timestamp;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
