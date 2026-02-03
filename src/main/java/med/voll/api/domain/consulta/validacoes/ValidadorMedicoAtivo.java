package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidadorMedicoAtivo {

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DadosDetalhamentoConsulta dados) {

        if(dados.idMedico() == null) {
            return;
        }
        var medicoAtivo = medicoRepository.findAtivoById(dados.idMedico());

        if (!medicoAtivo) {
            throw new ValidacaoException("O médico informado não está ativo no sistema");
        }
    }
}
