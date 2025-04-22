package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.dto.DadosTokenJWT;
import med.voll.api.domain.dto.UsuarioAutenticacaoDTO;
import med.voll.api.domain.model.Usuario;
import med.voll.api.domain.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/login","/login/"})
public class AutenticacaoController {
    //A classe AutenticacaoService não deve ser chamada diretamente
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid UsuarioAutenticacaoDTO usuarioAutenticacaoDTO){
        var authenticationToken = new UsernamePasswordAuthenticationToken(usuarioAutenticacaoDTO.login(), usuarioAutenticacaoDTO.senha());
        var authentication = authManager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
