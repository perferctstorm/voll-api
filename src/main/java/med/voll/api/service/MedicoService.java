package med.voll.api.service;

import med.voll.api.dto.AtualizarMedicoDTO;
import med.voll.api.dto.ListarMedicoDTO;
import med.voll.api.model.Medico;
import med.voll.api.repository.MedicoReporitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicoService {
    @Autowired
    MedicoReporitory reporitory;
    @Autowired
    ConverteDados conversor;

    public Medico salvar(ListarMedicoDTO medicoDTO){
        Medico medico = conversor.converteMedicoDTO(medicoDTO);
        medico.setAtivo((short)1);
        return reporitory.save(medico);
    }

    public Page<ListarMedicoDTO> listar(Pageable paginacao){
        return conversor.converteListaMedicos((Page<Medico>) reporitory.buscarAtivos(paginacao));
    }

    public void atualizar(AtualizarMedicoDTO medicoDTO) {
        Optional<Medico> medico = reporitory.findById(medicoDTO.id());

        if(medico.isPresent()) {
            medico.get().atualizar(medicoDTO);
        }
    }

    public void excluir(Long id) {
        Optional<Medico> medico = reporitory.findById(id);
        if(medico.isPresent())
            medico.get().excluir();
    }
}
