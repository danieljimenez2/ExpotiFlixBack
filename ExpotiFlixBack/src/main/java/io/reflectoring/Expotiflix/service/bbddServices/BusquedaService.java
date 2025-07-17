package io.reflectoring.Expotiflix.service.bbddServices;

import java.time.LocalDateTime;
import io.reflectoring.Expotiflix.model.Busqueda;
import io.reflectoring.Expotiflix.repository.BusquedaRepository;
import org.springframework.stereotype.Service;


@Service
public class BusquedaService {
    private final BusquedaRepository repository;

    public BusquedaService(BusquedaRepository repository) {
        this.repository = repository;
    }

    public void guardarBusqueda(String query, String tipo) {
        Busqueda b = new Busqueda(query, tipo, LocalDateTime.now());
        repository.save(b);
    }
}
