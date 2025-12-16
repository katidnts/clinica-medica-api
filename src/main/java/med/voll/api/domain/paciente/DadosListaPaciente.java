package med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotBlank;

public record DadosListaPaciente(
        @NotBlank
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf) {

    public DadosListaPaciente(Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getTelefone(),
                paciente.getCpf());
    }
}
