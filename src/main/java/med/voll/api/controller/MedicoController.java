package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.dto.AtualizarMedicoDTO;
import med.voll.api.domain.dto.ListarMedicoDTO;
import med.voll.api.domain.dto.MedicoDTO;
import med.voll.api.domain.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping({"/medicos","/medicos/"})
public class MedicoController {
    @Autowired
    MedicoService medicoService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid ListarMedicoDTO medicoDTO, UriComponentsBuilder uriBuilder){
        MedicoDTO atualizarMedicoDTO = medicoService.salvar(medicoDTO);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(atualizarMedicoDTO.id()).toUri();
        return ResponseEntity.created(uri).body(atualizarMedicoDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ListarMedicoDTO>> listar(@PageableDefault(size=20, sort = {"nome"}, direction = Sort.Direction.DESC) Pageable paginacao){
          return ResponseEntity.ok(medicoService.listar(paginacao));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid AtualizarMedicoDTO medicoDTO){
        AtualizarMedicoDTO medico = medicoService.atualizar(medicoDTO);
        return ResponseEntity.ok(medico);
    }

    @DeleteMapping({"/{id}","/{id}/"})
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        medicoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping({"/{id}","/{id}/"})
    public ResponseEntity detalhar(@PathVariable Long id){
        return ResponseEntity.ok(medicoService.detalhar(id));
    }
}
