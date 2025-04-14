package med.voll.api.domain.model;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.dto.AtualizarMedicoDTO;
import org.springframework.util.ObjectUtils;

@Entity
@Table(name = "medicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private Short ativo;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;

    public Medico(Long id, String nome, String email, String telefone, String crm, Especialidade especialidade, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.crm = crm;
        this.especialidade = especialidade;
        this.endereco = endereco;
    }

    public void atualizar(AtualizarMedicoDTO medicoDTO) {
        if(!ObjectUtils.isEmpty(medicoDTO.nome()))
            this.setNome(medicoDTO.nome());
        if(!ObjectUtils.isEmpty(medicoDTO.telefone()))
            this.setTelefone(medicoDTO.telefone());
        if(medicoDTO.endereco()!=null)
            this.endereco.atualizar(medicoDTO.endereco());
    }

    public void excluir() {
        this.setAtivo((short)0);
    }
}
