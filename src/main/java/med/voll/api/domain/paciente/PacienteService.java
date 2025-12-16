package med.voll.api.domain.paciente;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PacienteService {

    private final PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Paciente save(Paciente paciente) {
        return repository.save(paciente);
    }

    @Transactional
    public Paciente atualizar(Long id, Paciente pacienteAtualizado) {
        var paciente = repository.getReferenceById(id);

        if (pacienteAtualizado.getNome() != null) {
            paciente.setNome(pacienteAtualizado.getNome());
        }
        if (pacienteAtualizado.getEmail() != null) {
            paciente.setEmail(pacienteAtualizado.getEmail());
        }
        if (pacienteAtualizado.getTelefone() != null) {
            paciente.setTelefone(pacienteAtualizado.getTelefone());
        }
        if (pacienteAtualizado.getEndereco() != null) {
            paciente.getEndereco().atualizarDadosEndereco(pacienteAtualizado.getEndereco());
        }
        return paciente;
    }

    @Transactional
    public Page<Paciente> listarPacientes(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao);
    }

    public Optional<Paciente> obterPaciente(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id não pode ser nulo");
        }
        return repository.findById(id);
    }

    @Transactional
    public Optional<Paciente> inativar(Long id) {
        return repository.findById(id)
                .map(paciente -> {
                    if (paciente.isAtivo()) {
                        paciente.setAtivo(false);
                    }
                    return paciente;
                });

    }


}
