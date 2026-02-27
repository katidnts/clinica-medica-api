package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosConsulta dados) {

        var medicoComConsultaNoMesmoHorario = consultaRepository.existsByMedicoIdAndDataAndMotivoIsNull(dados.idMedico(), dados.data());

        if (medicoComConsultaNoMesmoHorario) {
            throw new ValidacaoException("Médico informado já possui consulta marcada nessa data e horário.");
        }
    }
}
