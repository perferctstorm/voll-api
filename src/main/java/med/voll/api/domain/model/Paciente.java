package med.voll.api.domain.model;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.dto.DadosAtualizacaoPaciente;
import med.voll.api.domain.dto.DadosCadastroPaciente;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;

    private String telefone;

    private String cpf;

    @Embedded
    private Endereco endereco;

    private Short ativo;

    public Paciente(DadosCadastroPaciente dados) {
        this.ativo = (short)1;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        Endereco endereco = new Endereco();
        endereco.setLogradouro(dados.endereco().logradouro());
        endereco.setBairro(dados.endereco().bairro());
        endereco.setCep(dados.endereco().cep());
        endereco.setCidade(dados.endereco().cidade());
        endereco.setUf(dados.endereco().uf());
        endereco.setNumero(dados.endereco().numero());
        endereco.setComplemento(dados.endereco().complemento());

        this.endereco = endereco;
    }

    public void atualizarInformacoes(DadosAtualizacaoPaciente dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizar(dados.endereco());
        }

    }

    public void excluir() {
        this.ativo = (short)0;
    }
}
