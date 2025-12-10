package med.voll.api.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DadosEndereco;

public record CadastroDadosPaciente(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf,
        @NotNull
        @Valid
        DadosEndereco endereco) {


    public Paciente toEntity() {
        Paciente paciente = new Paciente();
        paciente.setAtivo(true);
        paciente.setNome(this.nome);
        paciente.setEmail(this.email);
        paciente.setTelefone(this.telefone);
        paciente.setCpf(this.cpf);
        paciente.setEndereco(endereco.toEntity());

        return paciente;

    }
}
