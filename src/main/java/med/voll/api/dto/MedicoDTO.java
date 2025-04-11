package med.voll.api.dto;

import jakarta.persistence.*;
import med.voll.api.model.Endereco;
import med.voll.api.model.Especialidade;

public record MedicoDTO(Long id, String nome, String email, String telefone, String crm,
                        Especialidade especialidade, EnderecoDTO endereco) {
}
