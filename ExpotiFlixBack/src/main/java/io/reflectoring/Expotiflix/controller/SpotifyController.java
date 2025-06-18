package io.reflectoring.Expotiflix.controller;

import io.reflectoring.Expotiflix.service.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController // Indica que esta clase es un controlador REST (devuelve JSON directamente)
@RequestMapping("/spotify") // Define que todas las rutas dentro de este controlador comienzan con /spotify
public class SpotifyController {

    @Autowired // Inyecta automáticamente el servicio SpotifyService en esta clase
    private SpotifyService spotifyService;

    @GetMapping("/me") // Define una ruta GET: /spotify/me
    public ResponseEntity<String> getSpotifyUser(@RequestHeader("Authorization") String authorizationHeader) {
        ResponseEntity<String> spotifyResponse = spotifyService.getUserInfo(authorizationHeader);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // Opcional, por claridad

        // Solo devolvemos el cuerpo (body) de la respuesta de Spotify, no los headers
        return new ResponseEntity<>(spotifyResponse.getBody(), headers, spotifyResponse.getStatusCode());
    }
}