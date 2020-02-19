package br.com.cardoso.sistemaponto.controller;

import br.com.cardoso.sistemaponto.persistence.model.Usuario;
import br.com.cardoso.sistemaponto.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public Iterable findAll() {
        return usuarioService.obtemUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario findOne(@PathVariable Long id) {
        return usuarioService.getUsuario(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario create(@RequestBody Usuario usuario) {
        return usuarioService.adicionaUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        usuarioService.deletaUsuario(id);
    }

    @PutMapping("/{id}")
    public Usuario updateUsuario(@RequestBody Usuario usuario, @PathVariable Long id) {
        return usuarioService.atualizaUsuario(usuario, id);
    }
}
