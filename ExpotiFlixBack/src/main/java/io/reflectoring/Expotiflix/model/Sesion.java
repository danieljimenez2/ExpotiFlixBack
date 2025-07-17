package io.reflectoring.Expotiflix.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;



@Entity
    public class Sesion {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private LocalDateTime fechaInicio;

        @Column(length = 512)
        private String token;

    public Sesion()
    {
        this.fechaInicio = LocalDateTime.now();
    }
    public Sesion(String token)
    {
        this.token=token;
        this.fechaInicio = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
