package io.reflectoring.Expotiflix.controller;

import io.reflectoring.Expotiflix.service.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import io.reflectoring.Expotiflix.model.SpotifyProfileDTO;

@RestController // Indica que esta clase es un controlador REST (devuelve JSON directamente)
@RequestMapping("/me") // Define que todas las rutas dentro de este controlador comienzan con /spotify
public class SpotifyProfileController {

    @Autowired //Autowired sirve para inyectar servicios en una clase
    private SpotifyService spotifyService;

    @GetMapping("/profile")
    public ResponseEntity<SpotifyProfileDTO> getSpotifyUser(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        SpotifyProfileDTO userInfo = spotifyService.getUserInfo(authorizationHeader);
        return ResponseEntity.ok(userInfo);
    }


}