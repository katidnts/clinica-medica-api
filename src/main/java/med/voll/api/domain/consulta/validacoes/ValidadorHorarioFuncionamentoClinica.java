package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;

import java.time.DayOfWeek;

public class ValidadorHorarioFuncionamentoClinica {

    public void validar(DadosDetalhamentoConsulta dados) {
        var dataDaConsulta = dados.data();
        var domingo = dataDaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horarioAntesDaAbertura = dataDaConsulta.getHour() < 7;
        var horarioDepoisDoEncerramento = dataDaConsulta.getHour() > 18;

        if (domingo || horarioAntesDaAbertura || horarioDepoisDoEncerramento){
            throw new ValidacaoException("Consulta fora do horario de funcionamento da clínica.");
        }
    }
}
