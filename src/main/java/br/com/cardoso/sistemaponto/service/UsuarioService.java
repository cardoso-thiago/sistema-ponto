package br.com.cardoso.sistemaponto.service;

import br.com.cardoso.sistemaponto.persistence.model.Usuario;
import br.com.cardoso.sistemaponto.persistence.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Iterable obtemUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuario(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado na base de dados.");
        }
        return usuario.get();
    }

    public Usuario adicionaUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deletaUsuario(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado na base de dados.");
        }
        usuarioRepository.deleteById(id);
    }

    public Usuario atualizaUsuario(Usuario usuario, Long id) {
        if (usuario.getId() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id do usuário informado não corresponde ao id passado como parâmetro");
        }
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(id);
        if (usuarioEncontrado.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado na base de dados.");
        }
        return usuarioRepository.save(usuario);
    }
}
