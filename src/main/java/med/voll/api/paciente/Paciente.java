package med.voll.api.paciente;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import med.voll.api.endereco.Endereco;

@Entity
@Table(name = "pacientes")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Paciente {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Boolean isAtivo() {
        return ativo;
    }
}
