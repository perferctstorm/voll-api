package med.voll.api.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioAutenticacaoDTO(@NotBlank String login, @NotBlank String senha) {
}
