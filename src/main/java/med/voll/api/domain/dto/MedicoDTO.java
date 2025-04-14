package med.voll.api.domain.dto;

import med.voll.api.domain.model.Especialidade;

public record MedicoDTO(Long id, String nome, String email, String telefone, String crm,
                        Especialidade especialidade, EnderecoDTO endereco) {
}
