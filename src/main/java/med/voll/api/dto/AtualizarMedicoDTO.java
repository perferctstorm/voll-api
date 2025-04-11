package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.model.Especialidade;

public record AtualizarMedicoDTO(@NotNull Long id,
                                 String nome,
                                 String telefone,
                                 EnderecoDTO endereco) {

}
