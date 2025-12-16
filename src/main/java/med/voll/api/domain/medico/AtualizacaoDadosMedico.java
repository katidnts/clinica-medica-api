package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import med.voll.api.domain.endereco.DadosEndereco;

public record AtualizacaoDadosMedico(
        Long id,
        String nome,
        String telefone,
        @Email
        String email,
        @Valid
        DadosEndereco endereco) {

    public Medico toEntity() {
        Medico medico = new Medico();
        medico.setNome(this.nome);
        medico.setTelefone(this.telefone);
        medico.setEmail(this.email);
        if (medico.getEndereco() != null) {
            medico.setEndereco(this.endereco.toEntity());
        }
        return medico;
    }
}
