package io.reflectoring.Expotiflix.controller.bbddControllers;

import io.reflectoring.Expotiflix.model.Sesion;
import io.reflectoring.Expotiflix.repository.SesionRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/sesiones")
public class SesionController {

    private final SesionRepository sesionRepository;

    public SesionController(SesionRepository sesionRepository) {
        this.sesionRepository = sesionRepository;
    }

    @PostMapping
    public ResponseEntity<?> registrarSesion(@RequestHeader("Authorization") String bearerToken) {
        String token = bearerToken.replace("Bearer ", "");
        Sesion sesion = new Sesion(token); // ⚠️ Puedes no guardar el token completo si es sensible
        sesionRepository.save(sesion);
        return ResponseEntity.ok("Sesión registrada");
    }
}
