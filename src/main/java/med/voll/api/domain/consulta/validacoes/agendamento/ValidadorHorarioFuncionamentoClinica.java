package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta {

    public void validar(DadosConsulta dados) {
        var dataDaConsulta = dados.data();
        var domingo = dataDaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horarioAntesDaAbertura = dataDaConsulta.getHour() < 7;
        var horarioDepoisDoEncerramento = dataDaConsulta.getHour() > 18;

        if (domingo || horarioAntesDaAbertura || horarioDepoisDoEncerramento){
            throw new ValidacaoException("Consulta fora do horario de funcionamento da clínica.");
        }
    }
}
