package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.AtualizarMedicoDTO;
import med.voll.api.dto.ListarMedicoDTO;
import med.voll.api.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/medicos","/medicos/"})
public class MedicoController {
    @Autowired
    MedicoService medicoService;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid ListarMedicoDTO medicoDTO){
        medicoService.salvar(medicoDTO);
    }

    @GetMapping
    public Page<ListarMedicoDTO> listar(@PageableDefault(size=20, sort = {"nome"}, direction = Sort.Direction.DESC) Pageable paginacao){
        return medicoService.listar(paginacao).map(m->
                new ListarMedicoDTO(m.id(), m.nome(), m.email(),null, m.crm(), m.especialidade(),null));
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid AtualizarMedicoDTO medicoDTO){
        medicoService.atualizar(medicoDTO);
    }

    @DeleteMapping({"/{id}","/{id}/"})
    @Transactional
    public void excluir(@PathVariable Long id){
        medicoService.excluir(id);
    }
}
