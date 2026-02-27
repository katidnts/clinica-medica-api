package med.voll.api.domain.consulta;

import jakarta.transaction.Transactional;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import med.voll.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    @Autowired
    private List<ValidadorCancelamentoConsulta> validadoresDeCancelamento;

    public void agendar(DadosConsulta dados) {
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe");
        }
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Id do médico informado não existe");
        }

        validadores.forEach(v -> v.validar(dados));


        var medico = escolherMedico(dados);
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        Consulta consulta = new Consulta(null, medico, paciente, dados.data(), dados.especialidade(), null, Status.AGENDADA);
        consultaRepository.save(consulta);
    }


    private Medico escolherMedico(DadosConsulta dados) {

        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando o médico não for informado");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade().name(), dados.data());
    }

    @Transactional
    public void cancelarConsulta(Long id, DadosCancelamentoConsulta dadosCancelamentoConsulta) {


        if (!consultaRepository.existsById(id)) {
            throw new ValidacaoException("O id da consulta não existe em nosso sistema.");
        }

        var consulta = consultaRepository.getReferenceById(id);

        validadoresDeCancelamento.forEach(v -> v.validar(consulta, dadosCancelamentoConsulta));
        consulta.cancelar(dadosCancelamentoConsulta.motivo());
        System.out.println(dadosCancelamentoConsulta.motivo());


    }
}
