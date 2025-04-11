package med.voll.api.model;

import jakarta.persistence.Embeddable;
import lombok.*;
import med.voll.api.dto.EnderecoDTO;
import org.springframework.util.ObjectUtils;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String numero;
    private String complemento;

    public void atualizar(EnderecoDTO endereco) {
        if(!ObjectUtils.isEmpty(endereco.logradouro()))
            this.setLogradouro(endereco.logradouro());
        if(!ObjectUtils.isEmpty(endereco.bairro()))
            this.setBairro(endereco.bairro());
        if(!ObjectUtils.isEmpty(endereco.cep()))
            this.setCep(endereco.cep());
        if(!ObjectUtils.isEmpty(endereco.cidade()))
            this.setCidade(endereco.cidade());
        if(!ObjectUtils.isEmpty(endereco.uf()))
            this.setUf(endereco.uf());
        if(!ObjectUtils.isEmpty(endereco.numero()))
            this.setNumero(endereco.numero());
        if(!ObjectUtils.isEmpty(endereco.complemento()))
            this.setComplemento(endereco.complemento());
    }
}
