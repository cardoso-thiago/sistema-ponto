package br.com.cardoso.sistemaponto.persistence.repository;

import br.com.cardoso.sistemaponto.persistence.model.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{}
