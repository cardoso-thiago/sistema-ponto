package br.com.cardoso.sistemaponto.controller;

import br.com.cardoso.sistemaponto.exception.UsuarioIdMismatchException;
import br.com.cardoso.sistemaponto.exception.UsuarioNotFoundException;
import br.com.cardoso.sistemaponto.persistence.model.Usuario;
import br.com.cardoso.sistemaponto.persistence.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public Iterable findAll() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario findOne(@PathVariable Long id) throws UsuarioNotFoundException {
        return usuarioRepository.findById(id).orElseThrow(UsuarioNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario create(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws UsuarioNotFoundException {
        usuarioRepository.findById(id).orElseThrow(UsuarioNotFoundException::new);
        usuarioRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Usuario updateUsuario(@RequestBody Usuario usuario, @PathVariable Long id) throws UsuarioIdMismatchException, UsuarioNotFoundException {
        if (usuario.getId() != id) {
            throw new UsuarioIdMismatchException();
        }
        usuarioRepository.findById(id).orElseThrow(UsuarioNotFoundException::new);
        return usuarioRepository.save(usuario);
    }
}
