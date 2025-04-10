package med.voll.api.service;

import med.voll.api.dto.MedicoDTO;
import med.voll.api.model.Medico;
import med.voll.api.repository.MedicoReporitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {
    @Autowired
    MedicoReporitory reporitory;
    @Autowired
    ConverteDados conversor;

    public Medico salvar(MedicoDTO medicoDTO){
        Medico medico = conversor.converteMedicoDTO(medicoDTO);
        return reporitory.save(medico);
    }

    public Page<MedicoDTO> listar(Pageable paginacao){
        return conversor.converteListaMedicos( reporitory.findAll(paginacao) );
    }
}
