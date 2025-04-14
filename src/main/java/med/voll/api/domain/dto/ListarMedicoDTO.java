package med.voll.api.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.model.Especialidade;

public record ListarMedicoDTO(Long id,
      @NotBlank(message = "{nome.obrigatorio}") String nome,
      @NotBlank(message = "{email.obrigatorio}") @Email(message = "{email.invalido}") String email,
      @NotBlank(message = "{telefone.obrigatorio}") String telefone,
      @NotBlank(message = "{crm.obrigatorio}") @Pattern(message = "{crm.invalido}", regexp = "\\d{4,6}") String crm,
      @NotNull(message = "{especialidade.obrigatoria}") Especialidade especialidade,
      @NotNull(message = "{endereco.obrigatorio}") @Valid EnderecoDTO endereco) {}
