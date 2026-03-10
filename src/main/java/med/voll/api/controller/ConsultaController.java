package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private ConsultaService service;

    @PostMapping
    @Transactional
    public ResponseEntity agendarConsulta(@RequestBody @Valid DadosConsulta dadosConsulta){

        var dto = service.agendar(dadosConsulta);

        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity cancelarConsulta(@PathVariable Long id, @RequestBody @Valid DadosCancelamentoConsulta dadosCancelamentoConsulta){

        service.cancelarConsulta(id, dadosCancelamentoConsulta);

        return ResponseEntity.noContent().build();
    }
}
