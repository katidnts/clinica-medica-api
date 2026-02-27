package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DadosConsulta dados) {
        var pacienteAtivo = pacienteRepository.findAtivoById(dados.idPaciente());

        if (!pacienteAtivo) {
            throw new ValidacaoException("Paciente informado não está ativo no sitema da clínica");
        }
    }
}
