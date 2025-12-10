
package med.voll.api.medico;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DadosEndereco;

public record CadastroDadosMedico(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull
        @Valid
        DadosEndereco endereco) {

    public Medico toEntity() {
        Medico medico = new Medico();
        medico.setAtivo(true);
        medico.setNome(this.nome);
        medico.setEmail(this.email);
        medico.setCrm(this.crm);
        medico.setTelefone(this.telefone);
        medico.setEspecialidade(this.especialidade);
        medico.setEndereco(endereco.toEntity());

        return medico;
    }
}