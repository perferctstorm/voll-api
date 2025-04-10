package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.MedicoDTO;
import med.voll.api.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/medicos","/medicos/"})
public class MedicoController {
    @Autowired
    MedicoService medicoService;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid MedicoDTO medicoDTO){
        medicoService.salvar(medicoDTO);
    }

    @GetMapping
    public Page<MedicoDTO> listar(@PageableDefault(size=2, sort = {"nome"}, direction = Sort.Direction.DESC) Pageable paginacao){
        return medicoService.listar(paginacao).map(m->
                new MedicoDTO(m.nome(),
                        m.email(),
                        null,
                        m.crm(),
                        m.especialidade(),
                        null)
        );
    }
}
