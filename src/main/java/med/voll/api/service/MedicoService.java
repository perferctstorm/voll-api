package med.voll.api.service;

import med.voll.api.dto.AtualizarMedicoDTO;
import med.voll.api.dto.ListarMedicoDTO;
import med.voll.api.dto.MedicoDTO;
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

    public MedicoDTO salvar(ListarMedicoDTO medicoDTO){
        Medico medico = conversor.converteParaMedico(medicoDTO);
        medico.setAtivo((short)1);
        return conversor.converteParaMedicoDTO( reporitory.save(medico) );
    }

    public Page<ListarMedicoDTO> listar(Pageable paginacao){
        return conversor.converteParaListarMedicoDTO((Page<Medico>) reporitory.buscarAtivos(paginacao));
    }

    public AtualizarMedicoDTO atualizar(AtualizarMedicoDTO medicoDTO) {
        Optional<Medico> medico = reporitory.findById(medicoDTO.id());

        if(medico.isPresent()) {
            medico.get().atualizar(medicoDTO);
            return conversor.converteParaAtualizarMedicoDTO( medico.get() );
        }
        return null;
    }

    public void excluir(Long id) {
        Optional<Medico> medico = reporitory.findById(id);
        if(medico.isPresent())
            medico.get().excluir();
    }

    public MedicoDTO detalhar(Long id) {
        Optional<Medico> medico = reporitory.findByIdEqualsAndAtivoEquals(id, (short)1);
        if(medico.isPresent())
            return conversor.converteParaMedicoDTO(medico.get());
        return null;
    }
}
