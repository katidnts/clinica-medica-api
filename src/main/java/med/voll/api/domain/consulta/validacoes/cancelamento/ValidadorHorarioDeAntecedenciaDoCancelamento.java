package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioDeAntecedenciaDoCancelamento implements ValidadorCancelamentoConsulta {
    @Override
    public void validar(Consulta consulta, DadosCancelamentoConsulta dados) {
       var dataConsulta = consulta.getData();
       var agora = LocalDateTime.now();
       var diferencaEmHoras = Duration.between(agora, dataConsulta).toHours();
       if (dataConsulta.isBefore(agora.plusHours(24))){
           throw new ValidacaoException("A consulta só pode ser cancelada até 24h antes do horário marcado");
       }
    }
}
