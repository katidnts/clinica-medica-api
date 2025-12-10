package med.voll.api.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
        @NotBlank
        String logradouro,

        String numero,
        String complemento,
        @NotBlank
        String bairro,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,
        @NotBlank
        String cidade,
        @NotBlank
        String uf) {


    public static DadosEndereco fromEntity(Endereco endereco) {

        if(endereco == null) {
            return null;
        }
            DadosEndereco dadosEndereco = new DadosEndereco(endereco.getLogradouro(), endereco.getNumero(),
                    endereco.getComplemento(), endereco.getBairro(), endereco.getCep(), endereco.getCidade(), endereco.getUf());

            return dadosEndereco;
    }

    public Endereco toEntity() {
        Endereco endereco = new Endereco();
        endereco.setLogradouro(this.logradouro);
        endereco.setNumero(this.numero);
        endereco.setComplemento(this.complemento);
        endereco.setBairro(this.bairro);
        endereco.setCep(this.cep);
        endereco.setCidade(this.cidade);
        endereco.setUf(this.uf);
        return endereco;
    }
}
