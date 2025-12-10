package med.voll.api.medico;

import jakarta.validation.constraints.NotNull;

public record DadosListaMedicos(
        @NotNull
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade) {

    public DadosListaMedicos(Medico medico) {
        this(
                medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getEspecialidade());
    }
}
