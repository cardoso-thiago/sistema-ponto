package br.com.cardoso.sistemaponto.controller;

import br.com.cardoso.sistemaponto.persistence.model.BatidaPonto;
import br.com.cardoso.sistemaponto.persistence.model.ControleHoras;
import br.com.cardoso.sistemaponto.service.BatidaPontoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/punchClock")
public class BatidaPontoController {

    private final BatidaPontoService batidaPontoService;

    public BatidaPontoController(BatidaPontoService batidaPontoService) {
        this.batidaPontoService = batidaPontoService;
    }

    @GetMapping
    public Iterable findAll() {
        return batidaPontoService.obtemPontos();
    }

    @GetMapping("/userId/{id}")
    public ControleHoras findAllByUser(@PathVariable Long id) {
        return batidaPontoService.obtemPontoPorUsuario(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BatidaPonto create(@RequestBody BatidaPonto batidaPonto) {
        return batidaPontoService.adicionaPonto(batidaPonto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        batidaPontoService.deletaPonto(id);
    }
}
