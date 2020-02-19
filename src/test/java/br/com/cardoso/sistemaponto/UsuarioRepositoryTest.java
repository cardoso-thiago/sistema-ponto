package br.com.cardoso.sistemaponto;

import br.com.cardoso.sistemaponto.persistence.model.Usuario;
import br.com.cardoso.sistemaponto.persistence.repository.UsuarioRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UsuarioRepositoryTest {

    private static final String USER_NAME = "Thiago";
    private static final String USER_EMAIL = "thi.carsil@gmail.com";
    private static final String USER_CPF = "123.456.789-0";

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void bancoVazioRetornaOpcionalVazio() {
        Optional<Usuario> usuario = usuarioRepository.findById(1L);
        assertThat(usuario.isPresent()).isEqualTo(false);
    }

    @Test
    public void bancoComUsuarioRetornaOpcionalComValor() {
        Usuario usuario = new Usuario();
        usuario.setNome(USER_NAME);
        usuario.setEmail(USER_EMAIL);
        usuario.setCpf(USER_CPF);
        Usuario savedUser = usuarioRepository.save(usuario);

        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(savedUser.getId());
        assertThat(usuarioEncontrado.isPresent()).isEqualTo(true);
        if (usuarioEncontrado.isPresent()) {
            assertThat(usuarioEncontrado.get().getNome()).isEqualTo(USER_NAME);
            assertThat(usuarioEncontrado.get().getEmail()).isEqualTo(USER_EMAIL);
            assertThat(usuarioEncontrado.get().getCpf()).isEqualTo(USER_CPF);
        }
    }

    @After
    public void limpar() {
        usuarioRepository.deleteAll();
    }
}
