package io.reflectoring.Expotiflix.controller.bbddControllers;
import io.reflectoring.Expotiflix.service.bbddServices.BusquedaService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/busquedas")
public class BusquedaController {
    private final BusquedaService service;

    public BusquedaController(BusquedaService service) {
        this.service = service;
    }

    @PostMapping
    public void registrarBusqueda(@RequestParam String query, @RequestParam String tipo) {
        service.guardarBusqueda(query, tipo);
    }
}
