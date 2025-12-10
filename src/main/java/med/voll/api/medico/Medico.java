package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import med.voll.api.endereco.Endereco;

@Entity
@Table(name = "medicos")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Boolean isAtivo() {
        return ativo;
    }


}
