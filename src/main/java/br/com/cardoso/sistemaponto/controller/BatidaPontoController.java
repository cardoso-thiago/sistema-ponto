package br.com.cardoso.sistemaponto.controller;

import br.com.cardoso.sistemaponto.exception.BatidaPontoNotFoundException;
import br.com.cardoso.sistemaponto.exception.UsuarioNotFoundException;
import br.com.cardoso.sistemaponto.persistence.model.BatidaPonto;
import br.com.cardoso.sistemaponto.persistence.model.ControleHoras;
import br.com.cardoso.sistemaponto.persistence.model.Usuario;
import br.com.cardoso.sistemaponto.persistence.repository.BatidaPontoRepository;
import br.com.cardoso.sistemaponto.persistence.repository.UsuarioRepository;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.naming.ldap.Control;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/punchClock")
public class BatidaPontoController {

    @Autowired
    private BatidaPontoRepository batidaPontoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public Iterable findAll() {
        return batidaPontoRepository.findAll();
    }

    @GetMapping("/userId/{id}")
    public ControleHoras findAllByUser(@PathVariable Long id) throws BatidaPontoNotFoundException {
        List<BatidaPonto> batidasPonto = batidaPontoRepository.findByUser(id);
        ControleHoras ch = new ControleHoras();
        ch.setBatidaPontoList(batidasPonto);

        Seconds seconds = Seconds.seconds(0);
        for (int i = 0; i < batidasPonto.size() - 1; i++) {
            BatidaPonto batidaPonto = batidasPonto.get(i);
            if (batidaPonto.isEntrada()) {
                DateTime entrada = batidaPonto.getDataBatida();
                DateTime saida = new DateTime();
                BatidaPonto batidaSaida = batidasPonto.get(i + 1);
                if (batidaSaida != null) {
                    saida = batidaSaida.getDataBatida();
                }
                seconds = seconds.plus(Seconds.secondsBetween(saida, entrada));
            }
        }

        ch.setHorasTrabalhadas(String.valueOf(seconds.getSeconds()));
        return ch;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BatidaPonto create(@RequestBody BatidaPonto batidaPonto) throws UsuarioNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findById(batidaPonto.getIdUsuario());
        if (usuario.isEmpty()) {
            throw new UsuarioNotFoundException();
        }
        List<BatidaPonto> byUserAndDate = batidaPontoRepository.findByUser(batidaPonto.getIdUsuario());
        if (byUserAndDate.size() % 2 == 0) {
            batidaPonto.setEntrada(true);
        }
        return batidaPontoRepository.save(batidaPonto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws BatidaPontoNotFoundException {
        batidaPontoRepository.findById(id).orElseThrow(BatidaPontoNotFoundException::new);
        batidaPontoRepository.deleteById(id);
    }
}
