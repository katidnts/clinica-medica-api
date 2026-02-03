package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidadorHorarioDeAntecedencia {

    public void validar(DadosDetalhamentoConsulta dados) {
        var dataDaConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEMMinutos = Duration.between(agora, dataDaConsulta).toMinutes();
        if (diferencaEMMinutos < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos.");
        }
    }
}
