package io.reflectoring.Expotiflix.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service // Indica que esta clase es un servicio y puede ser inyectada con @Autowired
public class SpotifyService {

    // Crea una instancia de RestTemplate para hacer peticiones HTTP
    private final RestTemplate restTemplate = new RestTemplate();

    // Metodo que hace una petición GET al endpoint de Spotify para obtener la información del usuario
    public ResponseEntity<String> getUserInfo(String authHeader) {
        String url = "https://api.spotify.com/v1/me"; // URL de la API de Spotify para obtener el usuario autenticado

        HttpHeaders headers = new HttpHeaders(); // Crea los encabezados HTTP
        headers.set("Authorization", authHeader); // Establece la cabecera Authorization con el token Bearer
        HttpEntity<String> entity = new HttpEntity<>(headers); // Crea la entidad HTTP con los headers

        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    }
}