package med.voll.api.domain.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Endereco {

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;

    public void atualizarDadosEndereco(Endereco novoEndereco) {

        if (novoEndereco.logradouro != null) this.logradouro = novoEndereco.logradouro;
        if (novoEndereco.numero != null) this.numero = novoEndereco.numero;
        if (novoEndereco.bairro != null) this.bairro = novoEndereco.bairro;
        if (novoEndereco.cidade != null) this.cidade = novoEndereco.cidade;
        if (novoEndereco.uf != null) this.uf = novoEndereco.uf;
        if (novoEndereco.cep != null) this.cep = novoEndereco.cep;
    }
}
