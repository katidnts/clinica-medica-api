package med.voll.api.domain.medico;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicoService {

    private final MedicoRepository repository;

    public MedicoService(MedicoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Medico save(Medico medico) {
        return repository.save(medico);
    }

    @Transactional
    public Medico atualizar(Long id, Medico medicoAtualizado) {
        Medico medico = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Não foi encontrado um médico com o id: " + id));

        if (medicoAtualizado.getNome() != null) {
            medico.setNome(medicoAtualizado.getNome());
        }
        if (medicoAtualizado.getTelefone() != null) {
            medico.setTelefone(medicoAtualizado.getTelefone());
        }
        if (medicoAtualizado.getEmail() != null) {
            medico.setEmail(medicoAtualizado.getEmail());
        }
        if (medicoAtualizado.getEndereco() != null) {
            medico.getEndereco().atualizarDadosEndereco(medicoAtualizado.getEndereco());
        }
        if (medicoAtualizado.getCrm() != null) {
            throw new IllegalStateException("CRM não pode ser atualizado!");
        }

        return medico;
    }

    public Page<Medico> listarMedicos(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao);

    }

    public Optional<Medico> obterMedico(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Id não pode ser nulo");
        }
        return repository.findById(id);
    }

    @Transactional
    public Optional<Medico> inativar(Long id) {
        return repository.findById(id)
                .map(medico -> {
                    if (medico.isAtivo()) {
                        medico.setAtivo(false);
                    }
                    return medico;
                });

    }
}
