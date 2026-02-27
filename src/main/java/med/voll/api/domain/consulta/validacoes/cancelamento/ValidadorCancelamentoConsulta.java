package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.stereotype.Component;

@Component
public interface ValidadorCancelamentoConsulta {

    public void validar(Consulta consulta, DadosCancelamentoConsulta dados);
}
