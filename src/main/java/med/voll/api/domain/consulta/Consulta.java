package med.voll.api.domain.consulta;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.*;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "consultas")
@Entity(name = "consulta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;


    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Enumerated(EnumType.STRING)
    private MotivoCancelamento motivo;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Consulta(Medico medico, Paciente paciente,
                    LocalDateTime data, Especialidade especialidade) {

        this.medico = medico;
        this.paciente = paciente;
        this.data = data;
        this.especialidade = especialidade;
        this.status = Status.AGENDADA;
    }

    public void cancelar(MotivoCancelamento motivo){

        if (this.status != Status.AGENDADA) {
            throw new ValidacaoException("Somente consultas agendadas podem ser canceladas");
        }

        this.motivo = motivo;
        this.status = Status.CANCELADA;

    }
}
