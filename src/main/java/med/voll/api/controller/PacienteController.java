package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private final PacienteService service;

    public PacienteController(PacienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity cadastrarPacientes(@RequestBody @Valid CadastroDadosPaciente dados, UriComponentsBuilder uriBuilder) {
        var paciente = dados.toEntity();
        service.save(paciente);

        var uri = uriBuilder.path("/pacientes/{id}")
                .buildAndExpand(paciente.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(new DadosDetalhamentoPaciente(paciente));
    }

    @GetMapping
    public Page<DadosListaPaciente> listarPacientes(@PageableDefault(page = 0, size = 10, sort = {"cpf"}) Pageable paginacao) {
        return service.listarPacientes(paginacao).map(DadosListaPaciente::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPaciente> obterPaciente(@PathVariable Long id) {

        return service.obterPaciente(id)
                .map(paciente -> ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente)))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PatchMapping("/{id}")
    public ResponseEntity atualizarPaciente(@PathVariable Long id, @RequestBody @Valid AtualizacaoDadosPaciente dadosPaciente) {
        var paciente = dadosPaciente.toEntity();
        service.atualizar(id, paciente);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity inativarPaciente(@PathVariable Long id) {
        Optional<Paciente> paciente = service.inativar(id);

        if (paciente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (!paciente.get().isAtivo()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente.get()));
    }
}
