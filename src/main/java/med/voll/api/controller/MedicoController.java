package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private final MedicoService service;

    public MedicoController(MedicoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroDadosMedico dados, UriComponentsBuilder uriBuilder) {
        Medico medico = dados.toEntity();
        service.save(medico);

        URI uri = uriBuilder.path("/medicos/{id}")
                .buildAndExpand(medico.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListaMedicos>> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        Page<DadosListaMedicos> page = service.listarMedicos(paginacao).map(DadosListaMedicos::new);
        return ResponseEntity.ok(page);  
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoMedico> obterMedico(@PathVariable Long id) {
        return service.obterMedico(id)
                .map(medico -> ResponseEntity.ok(new DadosDetalhamentoMedico(medico)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity atualizarMedico(@PathVariable Long id, @RequestBody @Valid AtualizacaoDadosMedico dadosMedico) {

        Medico medico = dadosMedico.toEntity();
        Medico medicoAtualizado = service.atualizar(id, medico);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medicoAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity inativarMedico(@PathVariable Long id) {

        Optional<Medico> medico = service.inativar(id);
        if (medico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (!medico.get().isAtivo()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico.get()));

    }
}
