package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta{

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DadosConsulta dados) {

        if(dados.idMedico() == null) {
            return;
        }
        var medicoAtivo = medicoRepository.findAtivoById(dados.idMedico());

        if (!medicoAtivo) {
            throw new ValidacaoException("O médico informado não está ativo no sistema");
        }
    }
}
