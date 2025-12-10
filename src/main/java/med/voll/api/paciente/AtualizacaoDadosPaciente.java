package med.voll.api.paciente;

import jakarta.validation.Valid;
import med.voll.api.endereco.DadosEndereco;

public record AtualizacaoDadosPaciente(

        Long id,
        String nome,
        String email,
        String telefone,
        @Valid
        DadosEndereco endereco) {

    public Paciente toEntity() {
        Paciente paciente = new Paciente();
        paciente.setNome(this.nome);
        paciente.setEmail(this.email);
        paciente.setTelefone(this.telefone);
        if (paciente.getEndereco() != null) {
            paciente.setEndereco(this.endereco.toEntity());
        }
        return paciente;
    }
}
